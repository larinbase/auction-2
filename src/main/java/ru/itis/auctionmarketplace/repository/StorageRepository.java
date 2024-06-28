package ru.itis.auctionmarketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.auctionmarketplace.model.StorageEntity;

import java.util.Optional;
import java.util.UUID;

public interface StorageRepository extends JpaRepository<StorageEntity, UUID> {
    Optional<StorageEntity> findByAccountUsername(String username);

}
