package ru.itis.auctionmarketplace.dto.response;

import java.util.UUID;

public record AccountResponse(
        UUID id,
        String username,
        String password) {
}
