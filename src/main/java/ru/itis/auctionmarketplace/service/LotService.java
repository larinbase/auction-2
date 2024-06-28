package ru.itis.auctionmarketplace.service;

import ru.itis.auctionmarketplace.dto.request.LotRequest;
import ru.itis.auctionmarketplace.dto.response.LotResponse;
import ru.itis.auctionmarketplace.dto.response.LotWithBidsResponse;

import java.util.UUID;

public interface LotService {

    LotWithBidsResponse findWithBidsById(UUID id);

    LotResponse save(LotRequest lotRequest);

    void delete(UUID id);
}
