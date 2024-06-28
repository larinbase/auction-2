package ru.itis.auctionmarketplace.dto.request;

import ru.itis.auctionmarketplace.security.roles.AuctionRoleType;

import java.util.UUID;

public record AuctionRoleRequest(
    UUID auctionId,
    UUID accountId,
    AuctionRoleType type
) {
}
