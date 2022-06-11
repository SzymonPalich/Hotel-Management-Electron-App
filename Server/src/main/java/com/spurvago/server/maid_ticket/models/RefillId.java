package com.spurvago.server.maid_ticket.models;

import com.spurvago.database.MaidTicket;
import com.spurvago.database.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RefillId implements Serializable {
    private MaidTicket maidTicket;
    private Product product;
}
