package ru.itis.auctionmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.auctionmarketplace.security.roles.CommunityRoleType;
import ru.itis.auctionmarketplace.dto.request.AccountRequest;
import ru.itis.auctionmarketplace.dto.response.AccountResponse;
import ru.itis.auctionmarketplace.exception.AccountNotFoundException;
import ru.itis.auctionmarketplace.mapper.AccountMapper;
import ru.itis.auctionmarketplace.model.AccountEntity;
import ru.itis.auctionmarketplace.repository.AccountRepository;
import ru.itis.auctionmarketplace.service.AccountService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponse save(AccountRequest request) {
        AccountEntity account = accountMapper.toEntity(request);
        log.info("account: {}", account.toString());
        return accountMapper.toResponse(accountRepository.save(account));
    }

    @Override
    public List<AccountResponse> findAll() {
        return accountRepository.findAll()
                .stream().map(accountMapper::toResponse)
                .toList();
    }

    @Override
    public AccountResponse findByUsername(String username) {
        return accountRepository.findByUsername(username)
                .map(accountMapper::toResponse)
                .orElseThrow(() -> new AccountNotFoundException(username));
    }

    @Override
    public AccountResponse updateRole(UUID id, CommunityRoleType communityRoleType) {
        AccountEntity account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        account.setCommunityRoleType(communityRoleType);

        return accountMapper.toResponse(accountRepository.save(account));
    }

    @Override
    public void updateBalanceById(UUID id, BigDecimal amount) {
        AccountEntity account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        account.setBalance(amount);

        accountRepository.save(account);
    }

    @Override
    public void updateBalanceByUsername(String username, BigDecimal amount) {
        AccountEntity account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException(username));

        account.setBalance(amount);

        accountRepository.save(account);
    }


//    @Override
//    public void update(AccountUpdateAllRequest request) throws AccountNotFoundException {
//        AccountEntity account = AccountEntity.builder()
//                .id(request.id())
//                .username(request.username())
//                .passwordHash(request.password())
//                .build();
//
//        accountRepository.update(account);
//    }

    @Override
    public void delete(UUID id) {
        accountRepository.deleteById(id);
    }

    @Override
    public BigDecimal getBalanceByUsername(String username) {
        return accountRepository.findByUsername(username).get().getBalance();

    }
}
