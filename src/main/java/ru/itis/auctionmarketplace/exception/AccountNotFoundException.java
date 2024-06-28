package ru.itis.auctionmarketplace.exception;

import java.util.UUID;

public class AccountNotFoundException extends NotFoundServiceException {
    public AccountNotFoundException(UUID id) {
        super("account with id %s not found".formatted(id));
    }

    public AccountNotFoundException(String name) {
        super("account with name %s not found".formatted(name));
    }
}
