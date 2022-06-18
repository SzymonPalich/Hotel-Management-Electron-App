package com.spurvago.server.client.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Model wyświetlania klientów w listach rozwijanych
 */
@Getter
@Setter
public class ClientSelect {
    private Long id;
    private String clientLabel;
}
