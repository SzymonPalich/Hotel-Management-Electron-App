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
    public Client create(Client newTestEntity) {
        return clientRepository.save(newTestEntity);
    }

    @Override
    public Client update(Client oldTestEntity, Client newTestEntity) {
        oldTestEntity.map(newTestEntity);

        return clientRepository.save(oldTestEntity);
    }

    @Override
    public void delete(Client testEntity) {
        clientRepository.delete(testEntity);
    }
}
