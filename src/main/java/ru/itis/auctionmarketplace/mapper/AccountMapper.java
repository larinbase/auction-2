package ru.itis.auctionmarketplace.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.itis.auctionmarketplace.dto.request.AccountRequest;
import ru.itis.auctionmarketplace.dto.response.AccountResponse;
import ru.itis.auctionmarketplace.model.AccountEntity;


@Mapper(componentModel = "spring")
@Component
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    AccountEntity toEntity(AccountRequest accountRequest);

    AccountResponse toResponse(AccountEntity accountEntity);
}


