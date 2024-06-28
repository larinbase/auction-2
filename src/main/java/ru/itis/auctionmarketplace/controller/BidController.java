package ru.itis.auctionmarketplace.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itis.auctionmarketplace.dto.request.BidRequest;
import ru.itis.auctionmarketplace.dto.response.BidResponse;
import ru.itis.auctionmarketplace.service.BidService;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bid")
public class BidController {

    private final BidService bidService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BidResponse save(@ModelAttribute @Validated BidRequest bidRequest, Principal principal) {
        log.info(bidRequest.toString());
        return bidService.save(bidRequest, principal.getName());
    }
}
