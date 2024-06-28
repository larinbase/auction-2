package ru.itis.auctionmarketplace.dto.request;

import ru.itis.auctionmarketplace.security.roles.CommunityRoleType;

public record AccountUpdateRoleRequest(CommunityRoleType communityRoleType) {
}
