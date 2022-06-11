package com.spurvago.server.maid_ticket.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RefillFM {
    private Map<Long, Integer> products;
}
