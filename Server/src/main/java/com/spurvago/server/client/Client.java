package com.spurvago.server.client;

import com.spurvago.components.IMappableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
public class Client implements IMappableEntity<Client> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String surname;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String phoneNumber;

    @Override
    public void map(Client source) {
        this.setName(source.getName());
        this.setSurname(source.getSurname());
        this.setEmail(source.getEmail());
        this.setPhoneNumber(source.getPhoneNumber());
    }
}
