package ru.itis.auctionmarketplace.dto.response;

import ru.itis.auctionmarketplace.dto.Status;

import java.util.UUID;

public record LotResponse(
    UUID id,
    String name,
    String description,
    Status status
    ) {
}
