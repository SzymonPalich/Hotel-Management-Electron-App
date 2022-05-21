package com.spurvago.server.maid_ticket;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import com.spurvago.database.MaidTicket;
import com.spurvago.server.maid_ticket.models.MaidTicketFM;
import com.spurvago.server.maid_ticket.models.MaidTicketVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public record MaidTicketService(MaidTicketRepository maidTicketRepository, MaidTicketMapper maidTicketMapper) {

    public MaidTicketVM find(long id) {
        MaidTicket entity = maidTicketRepository.findById(id);
        if (entity == null)
            throw new ResponseStatusException(NOT_FOUND);

        return maidTicketMapper.mapToVM(entity);
    }

    public ListPaginated<MaidTicketVM> getList(Pager pager, String search) {
        Pageable pageable = pager.makePageable();
        Page<MaidTicket> entities;
        if (Utils.isNullOrBlank(search)) {
            entities = maidTicketRepository.findAll(pageable);
        } else {
            List<String> words = List.of(search.split("\\s"));
            Specification<MaidTicket> specification = MaidTicketRepository.search(words);
            entities = maidTicketRepository.findAll(specification, pageable);
        }

        List<MaidTicketVM> entitiesDTO = maidTicketMapper.mapToList(entities.getContent());
        return new ListPaginated<>(entitiesDTO, pager,
                entities.getTotalElements(), entities.getTotalPages());
    }

    public MaidTicketVM create(MaidTicketFM newEntity) {
        // TODO Validator

        MaidTicket entity = maidTicketMapper.mapToEntity(newEntity);
        maidTicketRepository.save(entity);
        return maidTicketMapper.mapToVM(entity);
    }

    public MaidTicketVM update(long id, MaidTicketFM newEntity) {
        // TODO Validator
        MaidTicket entity = maidTicketRepository.findById(id);
        if (entity == null)
            throw new ResponseStatusException(NOT_FOUND);

        maidTicketMapper.mapToEntity(entity, newEntity);
        maidTicketRepository.save(entity);
        return maidTicketMapper.mapToVM(entity);
    }

    public void delete(long id) {
        MaidTicket entity = maidTicketRepository.findById(id);
        if (entity == null)
            throw new ResponseStatusException(NOT_FOUND);

        maidTicketRepository.delete(entity);
    }
}
