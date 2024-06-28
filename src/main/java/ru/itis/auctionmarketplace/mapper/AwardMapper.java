package ru.itis.auctionmarketplace.mapper;

import org.mapstruct.Mapper;
import ru.itis.auctionmarketplace.dto.response.AwardResponse;
import ru.itis.auctionmarketplace.model.AwardEntity;

@Mapper(componentModel = "spring", uses = {AccountMapper.class, LotMapper.class , BidMapper.class})
public interface AwardMapper {

     AwardResponse toResponse(AwardEntity awardEntity);
}
