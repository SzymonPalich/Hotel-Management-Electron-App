package com.spurvago.server.client;


import com.spurvago.components.Validator;
import com.spurvago.database.Client;
import com.spurvago.server.client.models.ClientFM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class ClientValidator extends Validator {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientValidator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //<editor-fold desc="validate()">

    /**
     * <b>firstName</b> nie może być puste i musi zawierać <= 48 znaków<br>
     * <b>lastName</b> nie może być puste i musi zawierać <= 48 znaków<br>
     * <b>email</b> musi być prawidłowym adresem email<br>
     * <b>phoneNumber</b> musi zawierać <= 16 znaków<br>
     * musi zostać podany <b>email</b> lub <b>phoneNumber</b>
     *
     * @return True, jeżeli obiekt przeszedł walidację
     */
    public boolean validate(ClientFM model) {
        if (!haveLength(model.getFirstName(), 0, 48))
            return false;
        if (!haveLength(model.getLastName(), 0, 48))
            return false;
        if (isEmpty(model.getEmail()) && isEmpty(model.getPhoneNumber()))
            return false;
        if ((!isEmpty(model.getPhoneNumber())
                && (clientRepository.existsByEmail(model.getEmail())
                || !haveLength(model.getPhoneNumber(), 9, 9))))
            return false;
        if ((!isEmpty(model.getEmail())
                && (clientRepository.existsByPhoneNumber(model.getPhoneNumber())
                || !isEmail(model.getEmail()))))
            return false;
        return true;
    }

    public boolean validate(ClientFM model, long previousId) {
        Optional<Client> previousEntity = clientRepository.findById(previousId);

        if (!haveLength(model.getFirstName(), 0, 48))
            return false;
        if (!haveLength(model.getLastName(), 0, 48))
            return false;
        if (isEmpty(model.getEmail()) && isEmpty(model.getPhoneNumber()))
            return false;
        if (!model.getPhoneNumber().equals(previousEntity.get().getPhoneNumber()))
            if ((!isEmpty(model.getPhoneNumber())
                    && (clientRepository.existsByPhoneNumber(model.getPhoneNumber())
                    || !haveLength(model.getPhoneNumber(), 9, 9))))
                return false;
        if (!model.getEmail().equals(previousEntity.get().getEmail()))
            if ((!isEmpty(model.getEmail())
                    && (clientRepository.existsByEmail(model.getEmail())
                    || !isEmail(model.getEmail()))))
                return false;
        return true;
    }
    //</editor-fold>
}
