package com.ads.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "campaign_product",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "bid", nullable = false)
    private double bid;

    @Column(name = "status", length = 32, columnDefinition = "varchar(32) default 'ACTIVE'", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CampaignStatus status = CampaignStatus.ACTIVE;

    public Campaign(LocalDateTime startDate, List<Product> products, String name, double bid) {
        this.startDate = startDate;
        this.products = products;
        this.name = name;
        this.bid = bid;
    }
}
