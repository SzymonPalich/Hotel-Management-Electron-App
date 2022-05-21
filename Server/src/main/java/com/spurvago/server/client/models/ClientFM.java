package com.spurvago.server.client.models;

import com.spurvago.components.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientFM {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

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
}
