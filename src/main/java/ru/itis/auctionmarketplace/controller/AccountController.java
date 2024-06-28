package ru.itis.auctionmarketplace.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itis.auctionmarketplace.dto.request.AccountTopUpBalanceRequest;
import ru.itis.auctionmarketplace.dto.request.AccountUpdateRoleRequest;
import ru.itis.auctionmarketplace.dto.request.RegistrationForm;
import ru.itis.auctionmarketplace.dto.response.AccountResponse;
import ru.itis.auctionmarketplace.dto.response.BalanceResponse;
import ru.itis.auctionmarketplace.service.AccountService;
import ru.itis.auctionmarketplace.service.AuthService;
import ru.itis.auctionmarketplace.service.CurrencyConverterService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;
    private final AuthService authService;
    private final CurrencyConverterService currencyConverterService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse save(@ModelAttribute RegistrationForm registrationForm) {
        log.info("save accountRequest: {}", registrationForm);
        authService.register(registrationForm);
        return accountService.findByUsername(registrationForm.username());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id){
        log.info("delete account id: {}", id);
        accountService.delete(id);
    }

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponse findByUsername(@PathVariable String username) {
        return accountService.findByUsername(username);
    }

    @PatchMapping("/{id}/update-role")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponse update(@PathVariable UUID id, @RequestBody AccountUpdateRoleRequest request){
        return accountService.updateRole(id, request.communityRoleType());
    }

    @PatchMapping("/update-balance")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public BalanceResponse update(@RequestBody @Validated AccountTopUpBalanceRequest request, Principal principal)  {
        log.info("update balance: {} - {}", request.currency(), request.amount());
        BigDecimal value = currencyConverterService.convertToRub(request.amount(), request.currency());
        log.info(String.valueOf(value));
        BigDecimal oldBalance = accountService.getBalanceByUsername(principal.getName());
        accountService.updateBalanceByUsername(principal.getName(), oldBalance.add(value));
        return new BalanceResponse(oldBalance.add(value));
    }
}
