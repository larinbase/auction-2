package ru.itis.auctionmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.auctionmarketplace.dto.response.AwardResponse;
import ru.itis.auctionmarketplace.exception.AccountNotFoundException;
import ru.itis.auctionmarketplace.exception.BidNotFoundException;
import ru.itis.auctionmarketplace.exception.LotNotFoundException;
import ru.itis.auctionmarketplace.mapper.AwardMapper;
import ru.itis.auctionmarketplace.model.AccountEntity;
import ru.itis.auctionmarketplace.model.AwardEntity;
import ru.itis.auctionmarketplace.model.BidEntity;
import ru.itis.auctionmarketplace.model.LotEntity;
import ru.itis.auctionmarketplace.repository.AccountRepository;
import ru.itis.auctionmarketplace.repository.AwardRepository;
import ru.itis.auctionmarketplace.repository.BidRepository;
import ru.itis.auctionmarketplace.repository.LotRepository;
import ru.itis.auctionmarketplace.service.AwardService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AwardServiceImpl implements AwardService {

    private final AwardRepository awardRepository;
    private final LotRepository lotRepository;
    private final BidRepository bidRepository;
    private final AccountRepository accountRepository;
    private final AwardMapper awardMapper;

    @Override
    public void create(UUID accountId, UUID lotId, UUID bidId) {
        AccountEntity account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
        LotEntity lotEntity = lotRepository.findById(lotId).orElseThrow(() -> new LotNotFoundException(lotId));
        BidEntity bidEntity = bidRepository.findById(bidId).orElseThrow(() -> new BidNotFoundException(bidId));
        awardRepository.save(
                AwardEntity.builder()
                        .account(account)
                        .lot(lotEntity)
                        .bid(bidEntity)
                        .build()
        );
    }

    @Override
    public List<AwardResponse> findAllByUsername(String username) {
        return awardRepository.findAllByAccountUsername(username)
                .stream()
                .map(awardMapper::toResponse)
                .collect(Collectors.toList());
    }
}
