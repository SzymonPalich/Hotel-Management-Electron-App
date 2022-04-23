package com.spurvago.server.client;

import com.spurvago.components.IBaseService;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public record ClientService(ClientRepository clientRepository) implements IBaseService<Client>{

    @Override
    public Client find(long id) {
        return clientRepository.findById(id);
    }

    @Override
    public ListPaginated<Client> getList(Pager pager) {
        Pageable pageable = pager.makePageable();
        Page<Client> entities = clientRepository.findAll(pageable);
        return new ListPaginated<>(entities, pager);
    }

    @Override
    public Client create(Client newEntity) {
        if(!Utils.emailValidation(newEntity.getEmail())){
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }
        return clientRepository.save(newEntity);
    }

    @Override
    public Client update(Client oldEntity, Client newEntity) {
        oldEntity.map(newEntity);

        return clientRepository.save(oldEntity);
    }

    @Override
    public void delete(Client Entity) {
        clientRepository.delete(Entity);
    }

    public ListPaginated<Client> getFiltered(String input, Pager pager) {
        Pageable pageable = pager.makePageable();
        if(input.isEmpty())
        {
            return null;
        }
        List<String> words = List.of(input.split("\\s"));
        Specification<Client> specification = ClientRepository.search(words);
        Page<Client> entities = clientRepository.findAll(specification, pageable);
        return new ListPaginated<>(entities, pager);
    }
}
