package ru.itis.auctionmarketplace.exception;

import java.util.UUID;

public class BidNotFoundException extends NotFoundServiceException{
    public BidNotFoundException(UUID id) {
        super("bid with id %s not found".formatted(id));
    }
}
