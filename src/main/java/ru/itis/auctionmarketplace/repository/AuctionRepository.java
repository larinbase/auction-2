package ru.itis.auctionmarketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.auctionmarketplace.model.AuctionEntity;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface AuctionRepository extends JpaRepository<AuctionEntity, UUID> {

    @Query(
            value = "select * from auction where auction.name <% ?1",
            nativeQuery = true
    )
    List<AuctionEntity> findAllWithSimilarName(String name);

    List<AuctionEntity> findAllByAccountId(UUID accountId);
}