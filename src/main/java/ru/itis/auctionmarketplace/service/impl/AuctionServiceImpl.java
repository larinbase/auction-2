package ru.itis.auctionmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.auctionmarketplace.dto.request.AuctionRequest;
import ru.itis.auctionmarketplace.dto.response.AccountResponse;
import ru.itis.auctionmarketplace.dto.response.AuctionResponse;
import ru.itis.auctionmarketplace.dto.response.AuctionWithLotsResponse;
import ru.itis.auctionmarketplace.exception.AuctionNotFoundException;
import ru.itis.auctionmarketplace.mapper.AuctionMapper;
import ru.itis.auctionmarketplace.model.AccountEntity;
import ru.itis.auctionmarketplace.model.AuctionEntity;
import ru.itis.auctionmarketplace.repository.AccountRepository;
import ru.itis.auctionmarketplace.repository.AuctionRepository;
import ru.itis.auctionmarketplace.service.AccountService;
import ru.itis.auctionmarketplace.service.AuctionService;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final AccountService accountService;
    private final AuctionMapper auctionMapper;

    @Override
    public List<AuctionResponse> findAll() {
        return auctionRepository.findAll().stream().map(auctionMapper::toResponse).toList();
    }

    @Override
    public AuctionResponse findById(UUID id) {
        return auctionRepository.findById(id)
                .map(auctionMapper::toResponse)
                .orElseThrow(() -> new AuctionNotFoundException(id));
    }

    @Override
    public List<AuctionResponse> findAllWithSimilarName(String name) {
        return auctionRepository.findAllWithSimilarName(name).stream()
                .map(auctionMapper::toResponse)
                .toList();
    }

    @Override
    public List<AuctionResponse> findAllByAccountId(UUID accountId) {
        return auctionRepository.findAllByAccountId(accountId)
                .stream().map(auctionMapper::toResponse)
                .toList();
    }

    @Override
    public AuctionWithLotsResponse findWithLotsById(UUID id) {
        return auctionRepository.findById(id)
                .map(auctionMapper::toWithLotsResponse)
                .orElseThrow(() -> new RuntimeException("Auction not found"));
    }

    @Override
    public AuctionResponse save(AuctionRequest request) {
        AuctionEntity auctionEntity = auctionMapper.toEntity(request);
        AccountResponse accountResponse = accountService.findByUsername(request.username());
        auctionEntity.setAccount(AccountEntity.builder()
                        .id(accountResponse.id())
                .build());
        return auctionMapper.toResponse(auctionRepository.save(auctionEntity));
    }

}
