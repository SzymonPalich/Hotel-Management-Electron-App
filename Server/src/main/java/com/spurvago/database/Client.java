package com.spurvago.database;

import com.spurvago.components.IBaseEntity;
import com.spurvago.components.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table
public class Client {
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
    private String email;

    @Getter
    private String phoneNumber;

    @OneToMany
    private List<Accommodation> accommodationList;

    public Client() {
    }

    public Client(Long id, String firstName, String lastName, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = (Utils.isNullOrBlank(email)) ? null : email;
        this.phoneNumber = (Utils.isNullOrBlank(phoneNumber)) ? null : phoneNumber;
    }

    public void setEmail(String email) {
        this.email = (Utils.isNullOrBlank(email)) ? null : email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = (Utils.isNullOrBlank(phoneNumber)) ? null : phoneNumber;
    }

    /**
     * <b>firstName</b> nie może być puste i musi zawierać < 50 znaków<br>
     * <b>lastName</b> nie może być puste i musi zawierać < 50 znaków<br>
     * <b>email</b> musi być prawidłowym adresem email<br>
     * <b>phoneNumber</b> musi zawierać < 16 znaków<br>
     * musi zostać podany <b>email</b> lub <b>phoneNumber</b>
     *
     * @return True, jeżeli obiekt przeszedł walidację
     */
    public boolean validate() {
        if (firstName.length() > 50 || firstName.isBlank())
            return false;
        if (lastName.length() > 50 || lastName.isBlank())
            return false;
        if (Utils.isNullOrBlank(email) && Utils.isNullOrBlank(phoneNumber))
            return false;
        if (!Utils.isNullOrBlank(phoneNumber) && phoneNumber.length() > 16)
            return false;
        return Utils.isNullOrBlank(email) || Utils.validateEmail(email);
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
