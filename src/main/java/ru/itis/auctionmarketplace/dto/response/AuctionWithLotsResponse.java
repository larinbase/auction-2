package ru.itis.auctionmarketplace.dto.response;

import java.util.List;
import java.util.UUID;

public record AuctionWithLotsResponse(
        UUID id,
        String name,
        String status,
        AccountResponse account,
        List<LotResponse> lotList) {
}
