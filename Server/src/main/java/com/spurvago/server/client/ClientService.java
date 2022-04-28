package com.spurvago.server.client;

import com.spurvago.components.IBaseService;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public record ClientService(ClientRepository clientRepository) implements IBaseService<Client> {

    @Override
    public Client find(long id) {
        return clientRepository.findById(id);
    }

    @Override
    public ListPaginated<Client> getList(Pager pager) {
        Pageable pageable = pager.makePageable();
        Page<Client> entities = clientRepository.findAll(pageable);
        ListPaginated<Client> listPaginated = new ListPaginated<>(entities, pager);
        return listPaginated;
    }

    @Override
    public Client create(Client newEntity) {
        if (!newEntity.validate())
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        if (!checkConstraints(newEntity.getPhoneNumber(), newEntity.getEmail()))
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        return clientRepository.save(newEntity);
    }

    @Override
    public Client update(Client oldEntity, Client newEntity) {
        if (!newEntity.validate())
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);

        if (!Objects.equals(oldEntity.getPhoneNumber(), newEntity.getPhoneNumber())
                && newEntity.getPhoneNumber() != null
                && clientRepository.existsByPhoneNumber(newEntity.getPhoneNumber()))
                throw new ResponseStatusException(UNPROCESSABLE_ENTITY);

        if (!Objects.equals(oldEntity.getEmail(), newEntity.getEmail())
                && newEntity.getEmail() != null
                && clientRepository.existsByEmail(newEntity.getEmail()))
                throw new ResponseStatusException(UNPROCESSABLE_ENTITY);

        oldEntity.map(newEntity);

        return clientRepository.save(oldEntity);
    }

    @Override
    public void delete(Client Entity) {
        clientRepository.delete(Entity);
    }

    public ListPaginated<Client> getFiltered(String input, Pager pager) {
        Pageable pageable = pager.makePageable();

        if (input.isEmpty())
            return null;

        List<String> words = List.of(input.split("\\s"));
        if (words.size() > 4)
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);

        Specification<Client> specification = ClientRepository.search(words);

        Page<Client> entities = clientRepository.findAll(specification, pageable);
        return new ListPaginated<>(entities, pager);
    }

    private boolean checkConstraints(String phoneNumber, String email){
        if(clientRepository.existsByPhoneNumber(phoneNumber))
            return false;
        if(clientRepository.existsByEmail(email))
            return false;
        return true;
    }
}
