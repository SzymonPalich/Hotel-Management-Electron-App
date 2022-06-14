package com.spurvago.server.maid_ticket.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Lista produktów uzupełnionych w minibarku wraz z ilością
 */
@Getter
@Setter
public class RefillFM {
    private Map<Long, Integer> products;
}
