package com.spurvago.server.client;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import com.spurvago.database.Client;
import com.spurvago.server.client.models.ClientFM;
import com.spurvago.server.client.models.ClientSelect;
import com.spurvago.server.client.models.ClientVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

/**
 * Serwis klientów
 * @param clientRepository
 * @param clientMapper
 * @param clientValidator
 */
@Service
public record ClientService(ClientRepository clientRepository,
                            ClientMapper clientMapper,
                            ClientValidator clientValidator) {

    public ClientVM find(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        Client entity;
        if (optionalClient.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalClient.get();

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

        List<ClientVM> entitiesDTO = clientMapper.mapToList(entities.getContent());
        return new ListPaginated<>(entitiesDTO, pager,
                entities.getTotalElements(), entities.getTotalPages());
    }

    public List<ClientSelect> getSelectList() {
        List<Client> entities = clientRepository.findAll();

        List<ClientSelect> entitiesDTO = clientMapper.mapToSelectList(entities);
        return entitiesDTO;
    }

    public ClientVM create(ClientFM newEntity) {
        if (!clientValidator.validate(newEntity)) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }

        Client entity = clientMapper.mapToEntity(newEntity);
        clientRepository.save(entity);
        return clientMapper.mapToVM(entity);
    }

    public ClientVM update(Long id, ClientFM newEntity) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        Client entity;
        if (optionalClient.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalClient.get();

        if (!clientValidator.validate(newEntity, id)) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }

        clientMapper.mapToEntity(entity, newEntity);
        clientRepository.save(entity);
        return clientMapper.mapToVM(entity);
    }

    public void delete(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        Client entity;
        if (optionalClient.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalClient.get();

        clientRepository.delete(entity);
    }
}
