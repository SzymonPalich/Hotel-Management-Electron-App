package com.spurvago.database;

import com.spurvago.server.maid_ticket.models.RefillId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table (name = "bar_replenish")
@IdClass(RefillId.class)
public class Refill {
    @Id
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "maid_ticket_id")
    private MaidTicket maidTicket;

    @Id
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Getter
    @Setter
    private int amount;

    public Refill(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Refill() {

    }
}
