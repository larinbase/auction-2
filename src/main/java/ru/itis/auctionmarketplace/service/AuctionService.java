package ru.itis.auctionmarketplace.service;

import ru.itis.auctionmarketplace.dto.request.AuctionRequest;
import ru.itis.auctionmarketplace.dto.response.AuctionResponse;
import ru.itis.auctionmarketplace.dto.response.AuctionWithLotsResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AuctionService {

    List<AuctionResponse> findAll();

    AuctionResponse findById(UUID id);

    List<AuctionResponse> findAllWithSimilarName(String name);

    List<AuctionResponse> findAllByAccountId(UUID accountId);

    AuctionWithLotsResponse findWithLotsById(UUID id);

    AuctionResponse save(AuctionRequest request);


}
