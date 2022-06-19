package com.spurvago.database;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Sprzątanie
 */
@Entity
@Table
public class MaidTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @Getter
    @Setter
    private Accommodation accommodation;

    @Getter
    @Setter
    private Date finalizationDate;

    @ManyToOne
    @Getter
    @Setter
    private Employee employee;

    @OneToMany
    @Getter
    @Setter
    @JoinTable(name = "bar_replenish")
    private List<Refill> products;

    public MaidTicket() {
    }
}
