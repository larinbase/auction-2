package ru.itis.auctionmarketplace.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itis.auctionmarketplace.dto.Status;
import ru.itis.auctionmarketplace.dto.request.AuctionCreationFormRequest;
import ru.itis.auctionmarketplace.dto.request.AuctionRequest;
import ru.itis.auctionmarketplace.dto.request.AuctionRoleRequest;
import ru.itis.auctionmarketplace.dto.response.AuctionResponse;
import ru.itis.auctionmarketplace.security.roles.AuctionRoleType;
import ru.itis.auctionmarketplace.service.AccountService;
import ru.itis.auctionmarketplace.service.AuctionService;
import ru.itis.auctionmarketplace.service.RoleService;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/auction")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;
    private final AccountService accountService;
    private final RoleService roleService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AuctionResponse> findAll(){
         return auctionService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AuctionResponse create(@ModelAttribute AuctionCreationFormRequest request){
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        log.info(principal.getName());
        AuctionRequest auctionRequest = new AuctionRequest(request.name(), Status.OPEN, principal.getName());
        AuctionResponse auctionResponse = auctionService.save(auctionRequest);
        roleService.save(new AuctionRoleRequest(auctionResponse.id(), accountService.findByUsername(principal.getName()).id(), AuctionRoleType.EDITOR));
        return auctionResponse;
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<AuctionResponse> findAllWithSimilarName(@PathVariable("name") String name){
        return auctionService.findAllWithSimilarName(name);
    }

}
