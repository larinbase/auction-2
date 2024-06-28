package ru.itis.auctionmarketplace.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.itis.auctionmarketplace.dto.request.BidRequest;
import ru.itis.auctionmarketplace.dto.response.BidResponse;
import ru.itis.auctionmarketplace.model.BidEntity;

@Mapper(componentModel = "spring")
@Component
public interface BidMapper {

    @Mapping(target = "id", ignore = true)
    BidEntity toEntity(BidRequest bidRequest);


    BidResponse toResponse(BidEntity bidEntity);

}
