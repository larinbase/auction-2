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
@Table(name = "lot")
public class LotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private AuctionEntity auction;

    @OneToMany(mappedBy = "lot", cascade = CascadeType.ALL)
    private List<BidEntity> bidList;
}
