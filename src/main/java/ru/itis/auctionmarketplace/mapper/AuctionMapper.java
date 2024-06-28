package ru.itis.auctionmarketplace.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.itis.auctionmarketplace.dto.request.AuctionRequest;
import ru.itis.auctionmarketplace.dto.response.AccountResponse;
import ru.itis.auctionmarketplace.dto.response.AuctionResponse;
import ru.itis.auctionmarketplace.dto.response.AuctionWithLotsResponse;
import ru.itis.auctionmarketplace.dto.response.LotResponse;
import ru.itis.auctionmarketplace.model.AuctionEntity;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
@Component
public interface AuctionMapper {

    @Mapping(target = "id", ignore = true)
    AuctionEntity toEntity(AuctionRequest auctionRequest);


    AuctionResponse toResponse(AuctionEntity auctionEntity);


    AuctionWithLotsResponse toWithLotsResponse(AuctionEntity auctionEntity);


}
