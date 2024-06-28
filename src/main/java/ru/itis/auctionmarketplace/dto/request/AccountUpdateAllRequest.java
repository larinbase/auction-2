package ru.itis.auctionmarketplace.dto.request;

import java.util.UUID;

public record AccountUpdateAllRequest(
        UUID id,
        String username,
        String password) {
}
