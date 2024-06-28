package ru.itis.auctionmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.auctionmarketplace.security.roles.CommunityRoleType;
import ru.itis.auctionmarketplace.dto.request.RegistrationForm;
import ru.itis.auctionmarketplace.exception.AccountAlreadyExistsException;
import ru.itis.auctionmarketplace.model.AccountEntity;
import ru.itis.auctionmarketplace.repository.AccountRepository;
import ru.itis.auctionmarketplace.service.AuthService;

import java.math.BigDecimal;


@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegistrationForm form) {
        if(accountRepository.existsByUsername(form.username())) {
            throw new AccountAlreadyExistsException("Account with name %s already exist".formatted(form.username()));
        }
        AccountEntity user = AccountEntity.builder()
                .username(form.username())
                .password(passwordEncoder.encode(form.password()))
                .communityRoleType(CommunityRoleType.USER)
                .balance(BigDecimal.valueOf(1000))
                .build();
        accountRepository.save(user);
    }


}