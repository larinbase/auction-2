package ru.itis.auctionmarketplace.model;

import jakarta.persistence.*;
import lombok.*;
import ru.itis.auctionmarketplace.security.roles.CommunityRoleType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
@ToString
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private CommunityRoleType communityRoleType;

    @Column(name = "balance")
    private BigDecimal balance;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private List<AuctionEntity> auctionList;

}
