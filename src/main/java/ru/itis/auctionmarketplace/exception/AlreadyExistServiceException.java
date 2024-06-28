package ru.itis.auctionmarketplace.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistServiceException extends ServiceException {
    public AlreadyExistServiceException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
