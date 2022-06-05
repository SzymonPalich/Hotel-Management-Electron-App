package com.spurvago.server.maid_ticket;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import com.spurvago.database.MaidTicket;
import com.spurvago.database.Product;
import com.spurvago.database.Refill;
import com.spurvago.server.maid_ticket.models.MaidTicketFM;
import com.spurvago.server.maid_ticket.models.MaidTicketVM;
import com.spurvago.server.maid_ticket.models.RefillFM;
import com.spurvago.server.product.ProductRepository;
import com.spurvago.server.refill.RefillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public record MaidTicketService(MaidTicketRepository maidTicketRepository,
                                MaidTicketMapper maidTicketMapper,
                                MaidTicketValidator maidTicketValidator,
                                ProductRepository productRepository,
                                RefillRepository refillRepository) {

    public MaidTicketVM find(Long id) {
        Optional<MaidTicket> optionalMaidTicket = maidTicketRepository.findById(id);
        MaidTicket entity;
        if (optionalMaidTicket.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalMaidTicket.get();

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
        if (!maidTicketValidator.validate(newEntity)) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }

        MaidTicket entity = maidTicketMapper.mapToEntity(newEntity);
        maidTicketRepository.save(entity);
        return maidTicketMapper.mapToVM(entity);
    }

    public MaidTicketVM update(Long id, MaidTicketFM newEntity) {
        if (!maidTicketValidator.validate(newEntity)) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }

        Optional<MaidTicket> optionalMaidTicket = maidTicketRepository.findById(id);
        MaidTicket entity;
        if (optionalMaidTicket.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalMaidTicket.get();

        maidTicketMapper.mapToEntity(entity, newEntity);
        maidTicketRepository.save(entity);
        return maidTicketMapper.mapToVM(entity);
    }

    public void delete(Long id) {
        Optional<MaidTicket> optionalMaidTicket = maidTicketRepository.findById(id);
        MaidTicket entity;
        if (optionalMaidTicket.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalMaidTicket.get();

        maidTicketRepository.delete(entity);
    }

    public void refill(Long id, RefillFM refillEntity) {
        Optional<MaidTicket> optionalMaidTicket = maidTicketRepository.findById(id);
        MaidTicket maidTicketEntity;
        if (optionalMaidTicket.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        maidTicketEntity = optionalMaidTicket.get();

        List<Product> products = productRepository.findAll();
        List<Long> productId = new ArrayList<>();
        for (Product x : products) {
            productId.add(x.getId());
        }

        while (refillEntity.getRefill().keys().hasMoreElements()) {
            if (!productId.contains(refillEntity.getRefill().keys().nextElement())) {
                throw new ResponseStatusException(NOT_FOUND);
            }
        }

        while (refillEntity.getRefill().elements().hasMoreElements()) {
            Optional<Product> temp = productRepository.findById(refillEntity.getRefill().keys().nextElement());
            maidTicketEntity.setProducts(Collections.singletonList(new Refill(temp.get(), refillEntity.getRefill().elements().nextElement())));
            maidTicketRepository.save(maidTicketEntity);
        }
    }
}
