package ru.itis.auctionmarketplace.service;

import ru.itis.auctionmarketplace.dto.request.AuctionRoleRequest;
import ru.itis.auctionmarketplace.model.AuctionRoleEntity;
import ru.itis.auctionmarketplace.security.roles.Role;

import java.util.UUID;

public interface RoleService {
    boolean hasAnyRoleByAuctionId(UUID auctionId, Role... roles);

    void save(AuctionRoleRequest auctionRoleRequest);
}
