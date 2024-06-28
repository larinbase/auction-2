package ru.itis.auctionmarketplace.service;


import ru.itis.auctionmarketplace.dto.request.BidRequest;
import ru.itis.auctionmarketplace.dto.response.BidResponse;
public interface BidService {

    BidResponse save(BidRequest bidRequest, String username);
}
