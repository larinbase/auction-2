package ru.itis.auctionmarketplace.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "award")
public class AwardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private AccountEntity account;

    @ManyToOne
    private LotEntity lot;

    @ManyToOne
    private BidEntity bid;
}
