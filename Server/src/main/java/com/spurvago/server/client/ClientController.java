package com.spurvago.server.client;

import com.spurvago.components.IBaseController;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.database.Client;
import com.spurvago.server.client.models.ClientFM;
import com.spurvago.server.client.models.ClientVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientVM find(@PathVariable long id) {
        return clientService.find(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ListPaginated<ClientVM> getList(Pager pager, String search) {
        return clientService.getList(pager, search);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClientVM create(ClientFM newEntity) {
        return clientService.create(newEntity);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientVM update(@PathVariable Long id, ClientFM newEntity) {
        return clientService.update(id, newEntity);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }
}
