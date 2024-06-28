package ru.itis.auctionmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.auctionmarketplace.dto.request.AuctionRoleRequest;
import ru.itis.auctionmarketplace.model.AccountEntity;
import ru.itis.auctionmarketplace.model.AuctionEntity;
import ru.itis.auctionmarketplace.model.AuctionRoleEntity;
import ru.itis.auctionmarketplace.repository.AuctionRoleRepository;
import ru.itis.auctionmarketplace.security.roles.AuctionRoleType;
import ru.itis.auctionmarketplace.security.roles.Role;
import ru.itis.auctionmarketplace.service.RoleService;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service("RoleService")
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final AuctionRoleRepository auctionRoleRepository;

    @Override
    public boolean hasAnyRoleByAuctionId(UUID auctionId, Role... roles) {
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("method: hasAnyRoleByAuctionId, username: {}, auctionId: {}", username, auctionId);
        final Set<AuctionRoleType> auctionRoleTypes =
                auctionRoleRepository.findRoleTypesByAccountUsernameAndAuctionId(username, auctionId)
                        .stream().map(AuctionRoleEntity::getType).collect(Collectors.toSet());
        log.info("{} has roles: {}", username, auctionRoleTypes);
        for (Role role : roles) {
            if (auctionRoleTypes.stream().anyMatch(auctionRoleType -> auctionRoleType.includes(role))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void save(AuctionRoleRequest auctionRoleRequest) {
        AuctionRoleEntity auctionRoleEntity = AuctionRoleEntity.builder()
                .auction(AuctionEntity.builder().id(auctionRoleRequest.auctionId()).build())
                .account(AccountEntity.builder().id(auctionRoleRequest.accountId()).build())
                .type(auctionRoleRequest.type())
                .build();
        auctionRoleRepository.save(auctionRoleEntity);
    }
}
