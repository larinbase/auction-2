package ru.itis.auctionmarketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.auctionmarketplace.model.AuctionRoleEntity;
import ru.itis.auctionmarketplace.security.roles.AuctionRoleType;

import java.util.Set;
import java.util.UUID;

public interface AuctionRoleRepository extends JpaRepository<AuctionRoleEntity, Long> {
   Set<AuctionRoleEntity> findRoleTypesByAccountUsernameAndAuctionId(String username, UUID auctionId);
}
