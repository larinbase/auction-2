package ru.itis.auctionmarketplace.dto.response;

import java.util.UUID;

public record AuctionResponse(
        UUID id,
        String name,
        String status,
        AccountResponse account) {
}
