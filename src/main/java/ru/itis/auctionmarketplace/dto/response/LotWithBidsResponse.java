package ru.itis.auctionmarketplace.dto.response;

import ru.itis.auctionmarketplace.dto.Status;

import java.util.List;
import java.util.UUID;

public record LotWithBidsResponse(
        UUID id,
        String name,
        String description,
        Status status,
        List<BidResponse> bidList ) {
}
