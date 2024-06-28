package ru.itis.auctionmarketplace.exception;

import org.springframework.http.HttpStatus;

public class NotFoundServiceException extends ServiceException{
    public NotFoundServiceException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
