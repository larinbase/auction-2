package ru.itis.auctionmarketplace.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountTopUpBalanceRequest(

        @NotNull(message = "Сумма должна быть больше нуля")
        BigDecimal amount,

        @NotBlank
        String currency) {
}
