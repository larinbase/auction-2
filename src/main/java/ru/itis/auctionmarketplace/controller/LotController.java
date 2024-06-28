package ru.itis.auctionmarketplace.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.auctionmarketplace.dto.request.LotRequest;
import ru.itis.auctionmarketplace.dto.response.LotResponse;
import ru.itis.auctionmarketplace.service.AwardService;
import ru.itis.auctionmarketplace.service.LotService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/lot")
@RequiredArgsConstructor
public class LotController {

    private final LotService lotService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LotResponse save(@ModelAttribute LotRequest lotRequest){
        log.info("save lotRequest: {}", lotRequest);
        return lotService.save(lotRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id){
        log.info("delete lot id: {}", id);
        lotService.delete(id);
//        awardService.create();
    }

}
