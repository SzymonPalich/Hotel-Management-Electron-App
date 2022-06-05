package com.spurvago.server.maid_ticket.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Dictionary;

@Getter
@Setter
public class RefillFM {
    private Dictionary<Long, Integer> refill;
}
