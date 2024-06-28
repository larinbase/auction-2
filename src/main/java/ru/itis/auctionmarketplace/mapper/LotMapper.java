package ru.itis.auctionmarketplace.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.itis.auctionmarketplace.dto.request.AuctionRequest;
import ru.itis.auctionmarketplace.dto.request.LotRequest;
import ru.itis.auctionmarketplace.dto.response.AuctionResponse;
import ru.itis.auctionmarketplace.dto.response.AuctionWithLotsResponse;
import ru.itis.auctionmarketplace.dto.response.LotResponse;
import ru.itis.auctionmarketplace.dto.response.LotWithBidsResponse;
import ru.itis.auctionmarketplace.model.AuctionEntity;
import ru.itis.auctionmarketplace.model.LotEntity;

@Mapper(componentModel = "spring")
@Component
public interface LotMapper {

    @Mapping(target = "id", ignore = true)
    LotEntity toEntity(LotRequest lotRequest);


    LotResponse toResponse(LotEntity lotEntity);


    LotWithBidsResponse toWithBidsResponse(LotEntity lotEntity);

}
