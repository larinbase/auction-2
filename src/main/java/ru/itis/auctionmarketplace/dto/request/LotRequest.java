package ru.itis.auctionmarketplace.dto.request;

import java.util.UUID;

public record LotRequest(
        String name,
        String description,
        String status,
        UUID auctionId) {
}
