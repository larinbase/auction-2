package ru.itis.auctionmarketplace.service;

import ru.itis.auctionmarketplace.security.roles.CommunityRoleType;
import ru.itis.auctionmarketplace.dto.request.AccountRequest;
import ru.itis.auctionmarketplace.dto.response.AccountResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountResponse save(AccountRequest request);

    List<AccountResponse> findAll();
    AccountResponse findByUsername(String username);

    AccountResponse updateRole(UUID id, CommunityRoleType communityRoleType);

    void updateBalanceById(UUID id, BigDecimal amount);

    void updateBalanceByUsername(String username, BigDecimal amount);

    void delete(UUID id);

    BigDecimal getBalanceByUsername(String username);
}
