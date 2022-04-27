package com.spurvago.server.client;

import com.spurvago.components.IBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table
public class Client implements IBaseEntity<Client> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String phoneNumber;

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void map(Client source) {
        this.setFirstName(source.getFirstName());
        this.setLastName(source.getLastName());
        this.setEmail(source.getEmail());
        this.setPhoneNumber(source.getPhoneNumber());
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
