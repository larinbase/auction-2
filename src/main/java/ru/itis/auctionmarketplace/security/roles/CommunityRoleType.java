package ru.itis.auctionmarketplace.security.roles;

import java.util.HashSet;
import java.util.Set;

public enum CommunityRoleType implements Role {
    USER,
    ADMIN;

    private final Set<Role> children = new HashSet<>();

    static {
        ADMIN.children.add(USER);
        USER.children.add(AuctionRoleType.EDITOR);
    }

    @Override
    public boolean includes(Role role) {
        return this.equals(role) || children.stream().anyMatch(r -> r.includes(role));
    }
}
