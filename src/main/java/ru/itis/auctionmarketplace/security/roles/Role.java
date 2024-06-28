package ru.itis.auctionmarketplace.security.roles;

import java.util.Set;

public interface Role {
    boolean includes(Role role);

    static Set<Role> roots() {
        return Set.of(CommunityRoleType.ADMIN);
    }
}
