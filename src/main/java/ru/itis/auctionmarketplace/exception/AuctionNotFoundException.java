package ru.itis.auctionmarketplace.exception;

import java.util.UUID;

public class AuctionNotFoundException extends NotFoundServiceException{
    public AuctionNotFoundException(UUID id) {
        super("auction with id %s not found".formatted(id));
    }
}
