package ru.itis.auctionmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.auctionmarketplace.dto.request.BidRequest;
import ru.itis.auctionmarketplace.dto.response.BidResponse;
import ru.itis.auctionmarketplace.exception.AccountNotFoundException;
import ru.itis.auctionmarketplace.exception.LotNotFoundException;
import ru.itis.auctionmarketplace.exception.NotEnoughMoneyServiceException;
import ru.itis.auctionmarketplace.mapper.BidMapper;
import ru.itis.auctionmarketplace.model.BidEntity;
import ru.itis.auctionmarketplace.repository.AccountRepository;
import ru.itis.auctionmarketplace.repository.BidRepository;
import ru.itis.auctionmarketplace.repository.LotRepository;
import ru.itis.auctionmarketplace.service.AccountService;
import ru.itis.auctionmarketplace.service.BidService;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {

    private final AccountService accountService;
    private final BidRepository bidRepository;
    private final AccountRepository accountRepository;
    private final LotRepository lotRepository;
    private final BidMapper bidMapper;

    /**
     * Сохранение / Обновление ставки пользователся
     *
     * @param bidRequest информация о ставке
     * @param username   имя пользователя
     * @return ответ с информацией о сохранненой/обновленной ставке
     */
    @Transactional
    @Override
    public BidResponse save(BidRequest bidRequest, String username) {

        accountService.getBalanceByUsername(username);

        BidEntity bidEntity = new BidEntity();

        bidEntity.setAccount(accountRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException(username))
        );

        bidEntity.setLot(lotRepository.findById(bidRequest.lotId())
                .orElseThrow(() -> new LotNotFoundException(bidRequest.lotId())));

        // Проверяет есть ли запись с таким же account_id и lot_id
        // и если существует, то присваивает id bidEntity, чтобы обновить прошлую ставку
        // в противном случае сохраняет новую ставку
        bidRepository.findByAccountIdAndLotId(bidEntity.getAccount().getId(), bidEntity.getLot().getId())
                .ifPresent(entity -> {
                    bidEntity.setId(entity.getId());
                    bidEntity.setAmount(entity.getAmount());
                    bidEntity.setCreateDate(entity.getCreateDate());
                });

        // Проверяет хватает ли баланса на ставку и возвращает обновленный баланс пользователя
        BigDecimal balance = checkBalance(
                bidEntity.getAccount().getBalance(),
                bidRequest.amount(),
                bidEntity.getAmount() == null ? BigDecimal.valueOf(0) : bidEntity.getAmount()
        );

        // Обновляет размер ставки
        bidEntity.setAmount(bidRequest.amount());

        log.info("balance: {}", balance);

        accountService.updateBalanceById(bidEntity.getAccount().getId(), balance);

        return bidMapper.toResponse(bidRepository.save(bidEntity));
    }

    private BigDecimal checkBalance(BigDecimal balance, BigDecimal newBidAmount, BigDecimal lastBidAmount) {
        int result = balance.add(lastBidAmount).compareTo(newBidAmount);
        log.info("result of compare result {}", result);
        if (result <= 0) {
            throw new NotEnoughMoneyServiceException(newBidAmount);
        } else {
            return balance.add(lastBidAmount).subtract(newBidAmount);
        }
    }
}
