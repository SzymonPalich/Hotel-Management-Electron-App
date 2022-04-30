package com.spurvago.server.maid_ticket;

import com.spurvago.components.IBaseService;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record MaidService(MaidRepository maidRepository) implements IBaseService<MaidTicket> {

    @Override
    public MaidTicket find(long id) {
        return maidRepository.findById(id);
    }

    @Override
    public ListPaginated<MaidTicket> getList(Pager pager, String search) {
        Pageable pageable = pager.makePageable();
        Page<MaidTicket> entities = maidRepository.findAll(pageable);
        return new ListPaginated<>(entities, pager);
    }

    @Override
    public MaidTicket create(MaidTicket newEntity) {
        return maidRepository.save(newEntity);
    }

    @Override
    public MaidTicket update(MaidTicket oldEntity, MaidTicket newEntity) {
        oldEntity.map(newEntity);

        return maidRepository.save(oldEntity);
    }

    @Override
    public void delete(MaidTicket entity) {
        maidRepository.delete(entity);
    }

    public ListPaginated<MaidTicket> getFiltered(String input, Pager pager) {
        Pageable pageable = pager.makePageable();
        if(input.isEmpty()) return null;
        List<String> words = List.of(input.split("\\s"));
        Specification<MaidTicket> specification = MaidRepository.search(words);
        Page<MaidTicket> entities = maidRepository.findAll(specification, pageable);
        return new ListPaginated<>(entities, pager);
    }
}
