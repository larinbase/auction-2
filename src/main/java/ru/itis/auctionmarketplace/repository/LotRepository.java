package ru.itis.auctionmarketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.auctionmarketplace.model.LotEntity;

import java.util.UUID;

public interface LotRepository extends JpaRepository<LotEntity, UUID> {
}
