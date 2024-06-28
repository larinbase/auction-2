package ru.itis.auctionmarketplace.dto.response;

public record AwardResponse(
    AccountResponse account,
    LotResponse lot,
    BidResponse bid
) {
}
