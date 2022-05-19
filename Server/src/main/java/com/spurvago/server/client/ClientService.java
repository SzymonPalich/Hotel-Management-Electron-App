package com.spurvago.server.client;

import com.spurvago.components.IBaseService;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import com.spurvago.database.Client;
import com.spurvago.server.client.models.ClientFM;
import com.spurvago.server.client.models.ClientVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Objects;

import static com.spurvago.components.Utils.asLikeQuery;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public record ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {

    public ClientVM find(long id) {
        var entity = clientRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        return clientMapper.mapToVM(entity);
    }

    public ListPaginated<ClientVM> getList(Pager pager, String search) {
        Pageable pageable = pager.makePageable();
        Page<Client> entities;

        if (Utils.isNullOrBlank(search)) {
            entities = clientRepository.findAll(pageable);
        } else {
            List<String> words = List.of(search.split("\\s"));
            Specification<Client> specification = ClientRepository.search(words);
            entities = clientRepository.findAll(specification, pageable);
        }

        Page<ClientVM> entitiesDTO = entities.map(ClientVM::new);

        return new ListPaginated<>(entitiesDTO, pager);
    }

    public ClientVM create(ClientFM newEntity) {
        var entity = clientMapper.mapToEntity(newEntity);
        if (!entity.validate())
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        if (!checkConstraints(newEntity.getPhoneNumber(), newEntity.getEmail()))
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        clientRepository.save(entity);
        return clientMapper.mapToVM(entity);
    }

    public ClientVM update(long id, ClientFM newEntity) {
        Client entity = clientRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        if (!entity.validate())
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);

        if (!Objects.equals(entity.getPhoneNumber(), newEntity.getPhoneNumber())
                && newEntity.getPhoneNumber() != null
                && clientRepository.existsByPhoneNumber(newEntity.getPhoneNumber()))
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);

        if (!Objects.equals(entity.getEmail(), newEntity.getEmail())
                && newEntity.getEmail() != null
                && clientRepository.existsByEmail(newEntity.getEmail()))
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);

        clientMapper.mapToEntity(entity, newEntity);
        clientRepository.save(entity);
        return new ClientVM(entity);
    }

    public void delete(long id) {
        var entity = clientRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        clientRepository.delete(entity);
    }

    private boolean checkConstraints(String phoneNumber, String email){
        if(clientRepository.existsByPhoneNumber(phoneNumber))
            return false;
        return !clientRepository.existsByEmail(email);
    }
}
