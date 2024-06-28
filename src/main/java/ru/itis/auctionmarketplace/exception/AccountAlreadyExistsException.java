package ru.itis.auctionmarketplace.exception;

public class AccountAlreadyExistsException extends AlreadyExistServiceException {
    public AccountAlreadyExistsException(String message) {
        super(message);
    }
}