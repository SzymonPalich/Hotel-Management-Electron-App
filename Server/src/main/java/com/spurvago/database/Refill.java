package com.spurvago.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

/**
 * Uzupełnianie minibaru
 */
@Entity
@Table (name = "bar_replenish")
public class Refill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    private MaidTicket maidTicket;

    @Getter
    @Setter
    @ManyToOne
    private Product product;

    @Getter
    @Setter
    private int amount;

    public Refill(MaidTicket maidTicket, Product product, int amount) {
        this.maidTicket = maidTicket;
        this.product = product;
        this.amount = amount;
    }

    public Refill() {

    }
}
