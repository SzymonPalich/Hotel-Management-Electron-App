package com.spurvago.server.maid_ticket;

import com.spurvago.components.IBaseService;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.database.MaidTicket;
import com.spurvago.server.maid_ticket.models.MaidTicketVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public record MaidTicketService(MaidTicketRepository maidTicketRepository, MaidTicketMapper maidTicketMapper){

    public MaidTicketVM find(long id) {
        var entity = maidTicketRepository.findById(id);

        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        return maidTicketMapper.map(entity, new MaidTicketVM());
    }

    public ListPaginated<MaidTicket> getList(Pager pager, String search) {
        Pageable pageable = pager.makePageable();
        Page<MaidTicket> entities = maidTicketRepository.findAll(pageable);
        return new ListPaginated<MaidTicket>(entities, pager);
    }

    public MaidTicket create(MaidTicket newEntity) {
        return maidTicketRepository.save(newEntity);
    }

    public MaidTicket update(MaidTicket oldEntity, MaidTicket newEntity) {

        return maidTicketRepository.save(oldEntity);
    }

    public void delete(MaidTicket entity) {
        maidTicketRepository.delete(entity);
    }

    public ListPaginated<MaidTicket> getFiltered(String input, Pager pager) {
        Pageable pageable = pager.makePageable();
        if(input.isEmpty()) return null;
        List<String> words = List.of(input.split("\\s"));
        Specification<MaidTicket> specification = MaidTicketRepository.search(words);
        Page<MaidTicket> entities = maidTicketRepository.findAll(specification, pageable);
        return new ListPaginated<>(entities, pager);
    }
}
