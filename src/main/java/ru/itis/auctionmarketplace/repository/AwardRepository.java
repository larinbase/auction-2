package ru.itis.auctionmarketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.auctionmarketplace.model.AwardEntity;

import java.util.List;

public interface AwardRepository extends JpaRepository<AwardEntity, Long> {
    List<AwardEntity> findAllByAccountUsername(String username);
}
