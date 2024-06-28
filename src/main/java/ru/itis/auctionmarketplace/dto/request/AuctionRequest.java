package ru.itis.auctionmarketplace.dto.request;

import ru.itis.auctionmarketplace.dto.Status;

import java.util.UUID;

public record AuctionRequest(
        String name,
        Status status,
        String username) {
}
