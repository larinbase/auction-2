package ru.itis.auctionmarketplace.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.auctionmarketplace.service.*;
import ru.itis.auctionmarketplace.service.impl.RoleServiceImpl;

import java.security.Principal;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BaseController {

    private final AuctionService auctionService;
    private final AccountService accountService;
    private final LotService lotService;
    private final StorageService storageService;
    private final AwardService awardService;

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test successful ))))" ;
    }

    @GetMapping("/welcome")
    public String welcome(Principal principal, ModelMap modelMap) {
        modelMap.put("account", principal);
        return "welcome_page" ;
    }

    @GetMapping("/profile")
    public String profile(Principal principal, ModelMap modelMap) {
        modelMap.put("rewards", awardService.findAllByUsername(principal.getName()));
        modelMap.put("username", principal.getName());
        modelMap.put("account", principal);
        modelMap.put("balance", accountService.getBalanceByUsername(principal.getName()));
        return "profile_page";
    }

    @GetMapping("/main")
    public String mainPage(ModelMap modelMap, Principal principal) {
        log.info(auctionService.findAll().toString());
        UUID accountId = accountService.findByUsername(principal.getName()).id();
        modelMap.put("ownAuctionList", auctionService.findAllByAccountId(accountId));
        modelMap.put("auctionList", auctionService.findAll());
        modelMap.put("balance", accountService.getBalanceByUsername(principal.getName()));
        modelMap.put("account", principal);
        return "main_page";
    }

    @GetMapping("/auction/{id}")
    public String auctionPage(@PathVariable UUID id, ModelMap modelMap, Principal principal){
        log.info(auctionService.findWithLotsById(id).toString());
        modelMap.put("auction", auctionService.findWithLotsById(id));
        modelMap.put("balance", accountService.getBalanceByUsername(principal.getName()));
        modelMap.put("account", principal);
        return "auction_page";
    }

    @GetMapping("/lot/{id}")
    public String lotPage(@PathVariable UUID id, ModelMap modelMap, Principal principal){
        log.info(lotService.findWithBidsById(id).toString());
        modelMap.put("lot", lotService.findWithBidsById(id));
        modelMap.put("balance", accountService.getBalanceByUsername(principal.getName()));
        modelMap.put("account", principal);
        return "lot_page";
    }

}
