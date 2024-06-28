package ru.itis.auctionmarketplace.model;

import jakarta.persistence.*;
import lombok.*;
import ru.itis.auctionmarketplace.security.roles.AuctionRoleType;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auction_role")
public class AuctionRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    private AuctionEntity auction;

    @Enumerated(EnumType.STRING)
    private AuctionRoleType type;
}