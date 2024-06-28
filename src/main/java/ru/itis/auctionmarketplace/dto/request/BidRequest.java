package ru.itis.auctionmarketplace.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record BidRequest(
        @NotNull(message = "Поле не должно быть пустым")
        BigDecimal amount,
        @NotNull(message = "Поле не должно быть пустым")
        UUID lotId
) {
}
