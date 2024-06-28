package ru.itis.auctionmarketplace.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bid")
public class BidEntity implements Comparable<BidEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private LotEntity lot;

    @ManyToOne
    private AccountEntity account;

    @CreationTimestamp
    @Column(name = "create_date")
    private Instant createDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private Instant updateDate;

    @Override
    public int compareTo(BidEntity bid) {
        return this.getAmount().compareTo(bid.getAmount());
    }
}
