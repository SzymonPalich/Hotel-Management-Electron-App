package com.spurvago.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String productName;

    @Getter
    @Setter
    private BigDecimal retailPrice;

    @Getter
    @Setter
    private BigDecimal wholesalePrice;

    @ManyToMany
    @JoinTable(name = "bar_replenish")
    private List<Refill> maidTickets;
}
