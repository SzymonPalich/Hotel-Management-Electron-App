package com.spurvago.server.maintenance_ticket;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import com.spurvago.database.MaintenanceTicket;
import com.spurvago.server.maintenance_ticket.models.MaintenanceTicketFM;
import com.spurvago.server.maintenance_ticket.models.MaintenanceTicketVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public record MaintenanceTicketService(MaintenanceTicketRepository maintenanceTicketRepository,
                                       MaintenanceTicketMapper maintenanceTicketMapper,
                                       MaintenanceTicketValidator maintenanceTicketValidator) {
    public MaintenanceTicketVM find(Long id) {
        Optional<MaintenanceTicket> optionalMaintenanceTicket = maintenanceTicketRepository.findById(id);
        MaintenanceTicket entity;
        if (optionalMaintenanceTicket.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalMaintenanceTicket.get();

        return maintenanceTicketMapper.mapToVM(entity);
    }

    public ListPaginated<MaintenanceTicketVM> getList(Pager pager, String search) {
        Pageable pageable = pager.makePageable();
        Page<MaintenanceTicket> entities;
        if (Utils.isNullOrBlank(search)) {
            entities = maintenanceTicketRepository.findAll(pageable);
        } else {
            List<String> words = List.of(search.split("\\s"));
            Specification<MaintenanceTicket> specification = MaintenanceTicketRepository.search(words);
            entities = maintenanceTicketRepository.findAll(specification, pageable);
        }

        List<MaintenanceTicketVM> entitiesDTO = maintenanceTicketMapper.mapToList(entities.getContent());
        return new ListPaginated<>(entitiesDTO, pager,
                entities.getTotalElements(), entities.getTotalPages());
    }

    public MaintenanceTicketVM create(MaintenanceTicketFM newEntity) {
        if (!(maintenanceTicketValidator().validate(newEntity))) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }

        MaintenanceTicket entity = maintenanceTicketMapper.mapToEntity(newEntity);
        maintenanceTicketRepository.save(entity);
        return maintenanceTicketMapper.mapToVM(entity);
    }

    public MaintenanceTicketVM update(Long id, MaintenanceTicketFM newEntity) {
        if (!(maintenanceTicketValidator().validate(newEntity))) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }

        Optional<MaintenanceTicket> optionalMaintenanceTicket = maintenanceTicketRepository.findById(id);
        MaintenanceTicket entity;
        if (optionalMaintenanceTicket.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalMaintenanceTicket.get();

        maintenanceTicketMapper.mapToEntity(entity, newEntity);
        maintenanceTicketRepository.save(entity);
        return maintenanceTicketMapper.mapToVM(entity);
    }

    public void delete(Long id) {
        Optional<MaintenanceTicket> optionalMaintenanceTicket = maintenanceTicketRepository.findById(id);
        MaintenanceTicket entity;
        if (optionalMaintenanceTicket.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalMaintenanceTicket.get();

        maintenanceTicketRepository.delete(entity);
    }
}
