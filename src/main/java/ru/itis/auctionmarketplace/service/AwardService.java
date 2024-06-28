package ru.itis.auctionmarketplace.service;

import ru.itis.auctionmarketplace.dto.response.AwardResponse;

import java.util.List;
import java.util.UUID;

public interface AwardService {

    void create(UUID accountId, UUID lotId, UUID bidEntityId);

    List<AwardResponse> findAllByUsername(String username);
}
