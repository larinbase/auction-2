package ru.itis.auctionmarketplace.exception;

import java.util.UUID;

public class LotNotFoundException extends NotFoundServiceException {
    public LotNotFoundException(UUID id) {
        super("lot with id %s not found".formatted(id));
    }
}
