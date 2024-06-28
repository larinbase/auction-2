package ru.itis.auctionmarketplace.repository;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.auctionmarketplace.model.BidEntity;

import java.util.Optional;
import java.util.UUID;

public interface BidRepository extends JpaRepository<BidEntity, UUID> {

    Optional<BidEntity> findByAccountIdAndLotId(UUID id, UUID uuid);
}
