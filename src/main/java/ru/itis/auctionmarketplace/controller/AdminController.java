package ru.itis.auctionmarketplace.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.auctionmarketplace.dto.response.AccountResponse;
import ru.itis.auctionmarketplace.service.AccountService;
import ru.itis.auctionmarketplace.service.AuctionService;
import ru.itis.auctionmarketplace.service.RoleService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AccountService accountService;
    private final AuctionService auctionService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/accounts")
    public String admin(ModelMap modelMap) {
        List<AccountResponse> accountList = accountService.findAll();
        modelMap.put("accountList", accountList);
        return "admin_page";
    }

    @PreAuthorize("@RoleService.hasAnyRoleByAuctionId(#id, @AuctionRole.EDITOR)")
    @GetMapping("/auction/{id}")
    public String auction(@PathVariable UUID id, ModelMap modelMap) {
        log.info(auctionService.findWithLotsById(id).toString());
        modelMap.put("auction", auctionService.findWithLotsById(id));
        return "admin_auction_page";
    }


}
