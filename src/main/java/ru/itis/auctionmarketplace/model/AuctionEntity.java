package ru.itis.auctionmarketplace.model;

import jakarta.persistence.*;
import lombok.*;
import ru.itis.auctionmarketplace.dto.Status;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auction")
public class AuctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private AccountEntity account;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private List<LotEntity> lotList;
}
