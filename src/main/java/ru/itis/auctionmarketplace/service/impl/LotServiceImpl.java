package ru.itis.auctionmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.auctionmarketplace.dto.Status;
import ru.itis.auctionmarketplace.dto.request.LotRequest;
import ru.itis.auctionmarketplace.dto.response.LotResponse;
import ru.itis.auctionmarketplace.dto.response.LotWithBidsResponse;
import ru.itis.auctionmarketplace.exception.AuctionNotFoundException;
import ru.itis.auctionmarketplace.exception.LotNotFoundException;
import ru.itis.auctionmarketplace.mapper.LotMapper;
import ru.itis.auctionmarketplace.model.AccountEntity;
import ru.itis.auctionmarketplace.model.BidEntity;
import ru.itis.auctionmarketplace.model.LotEntity;
import ru.itis.auctionmarketplace.repository.AuctionRepository;
import ru.itis.auctionmarketplace.repository.AwardRepository;
import ru.itis.auctionmarketplace.repository.LotRepository;
import ru.itis.auctionmarketplace.service.AccountService;
import ru.itis.auctionmarketplace.service.AwardService;
import ru.itis.auctionmarketplace.service.LotService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LotServiceImpl implements LotService {

    private final AwardService awardService;
    private final AccountService accountService;
    private final LotRepository lotRepository;
    private final AuctionRepository auctionRepository;
    private final LotMapper lotMapper;

    @Override
    public LotWithBidsResponse findWithBidsById(UUID id) {
        return lotRepository.findById(id)
                .map(lotMapper::toWithBidsResponse)
                .orElseThrow(() -> new LotNotFoundException(id));
    }

    @Override
    public LotResponse save(LotRequest lotRequest) {
        LotEntity lotEntity = lotMapper.toEntity(lotRequest);
        lotEntity.setAuction(
                auctionRepository.findById(lotRequest.auctionId())
                        .orElseThrow(() -> new AuctionNotFoundException(lotRequest.auctionId()))
        );
        lotEntity.setStatus(Status.OPEN);
        return lotMapper.toResponse(lotRepository.save(lotEntity));
    }

    @Override
    public void delete(UUID id) {
        LotEntity lotEntity = lotRepository.findById(id).orElseThrow(() -> new LotNotFoundException(id));
        lotEntity.getBidList()
                .stream().max(BidEntity::compareTo)
                .ifPresentOrElse(bidEntity -> setAwardAndUpdateOwnerBalance(bidEntity, lotEntity.getAuction().getAccount()),
                        () -> log.info("no bids"));
        lotEntity.setStatus(Status.CLOSE);
        lotRepository.save(lotEntity);
    }

    private void setAwardAndUpdateOwnerBalance(BidEntity bid, AccountEntity owner){
        awardService.create(bid.getAccount().getId(), bid.getLot().getId(), bid.getId());
        accountService.updateBalanceById(owner.getId(), owner.getBalance().add(bid.getAmount()));
    }
}
