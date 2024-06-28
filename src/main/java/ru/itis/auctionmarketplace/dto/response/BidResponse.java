package ru.itis.auctionmarketplace.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record BidResponse(
        UUID id,
        BigDecimal amount,
        AccountResponse account,
        Instant createDate,
        Instant updateDate){
}
