package com.spurvago.server.client;

import com.spurvago.components.IBaseService;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}