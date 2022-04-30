package com.spurvago.server.client;

import com.spurvago.components.IBaseController;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/client")
public class ClientController implements IBaseController<Client> {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public Client find(Long id) {
        Client entity = clientService.find(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        return entity;
    }

    @Override
    public ListPaginated<Client> getList(Pager pager) {
        ListPaginated<Client> entities = clientService.getList(pager);

        return entities;
    }

    @Override
    public Client create(Client newTestEntity) {
        Client entity = clientService.create(newTestEntity);

        return entity;
    }

    @Override
    public Client update(Long id, Client newTestEntity) {
        Client oldEntity = clientService.find(id);
        if (oldEntity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        oldEntity = clientService.update(oldEntity, newTestEntity);

        return oldEntity;
    }

    @Override
    public void delete(Long id) {
        Client entity = clientService.find(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        clientService.delete(entity);
    }

    @GetMapping(path = "/find")
    public ListPaginated<Client> getFiltered(Pager pager, String search) {
        return clientService.getFiltered(pager, search);
    }
}
