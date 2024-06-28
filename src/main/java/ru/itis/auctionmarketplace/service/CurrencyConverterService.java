package ru.itis.auctionmarketplace.service;

import java.math.BigDecimal;

public interface CurrencyConverterService {
    BigDecimal convertToRub(BigDecimal amount, String currency);
}
