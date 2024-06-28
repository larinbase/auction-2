package ru.itis.auctionmarketplace.exception;

import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

public class NotEnoughMoneyServiceException extends ServiceException {
    public NotEnoughMoneyServiceException(BigDecimal amount) {
        super("Not enough money %s".formatted(amount), HttpStatus.BAD_REQUEST);
    }
}
