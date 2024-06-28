package ru.itis.auctionmarketplace.security.roles;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

public enum AuctionRoleType implements Role {
    EDITOR,
    VIEWER;

    private final Set<Role> children = new HashSet<>();

    static {
        EDITOR.children.add(VIEWER);
    }

    @Override
    public boolean includes(Role role) {
        return this.equals(role) || children.stream().anyMatch(r -> r.includes(role));
    }

    @Component("AuctionRole")
    @Getter
    static class SpringComponent {
        private final AuctionRoleType VIEWER = AuctionRoleType.VIEWER;
        private final AuctionRoleType EDITOR = AuctionRoleType.EDITOR;
    }
}
