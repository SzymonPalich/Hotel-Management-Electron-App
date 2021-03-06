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
import com.spurvago.server.security.UserManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

/**
 * Serwis zgłoszeń sprzątania
 * @param maidTicketRepository
 * @param maidTicketMapper
 * @param maidTicketValidator
 * @param productRepository
 * @param refillRepository
 * @param userManager
 */
@Service
public record MaidTicketService(MaidTicketRepository maidTicketRepository,
                                MaidTicketMapper maidTicketMapper,
                                MaidTicketValidator maidTicketValidator,
                                ProductRepository productRepository,
                                RefillRepository refillRepository,
                                UserManager userManager) {

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
        Pageable pageable;
        Page<MaidTicket> entities;

        var empRole = userManager.getRole();
        boolean isMaid = Objects.equals(empRole, "ROLE_MAID");

        if (!isMaid) {
            pageable = pager.makePageable();
        }
        else {
            pager.sort = "finalizationDate";
            pageable = pager.makePageableAsc();
        }


        if (Utils.isNullOrBlank(search)) {
            if (!isMaid) {
                entities = maidTicketRepository.findAll(pageable);
            }
            else {
                entities = maidTicketRepository.findAllByEmployeeIsNull(pageable);
            }
        } else {
            List<String> words = List.of(search.split("\\s"));
            Specification<MaidTicket> specification = MaidTicketRepository.search(words);
            if (!isMaid)
                entities = maidTicketRepository.findAll(specification, pageable);
            else
                entities = maidTicketRepository.findAllByEmployeeIsNull(specification, pageable);
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

        if (!maidTicketValidator.validate(entity)) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }

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

    /**
     * Finalizowanie sprzątania po uzupełnieniu minibarku przez osobę sprzątającą
     * @param id
     * @param refillEntity
     */
    public void refill(Long id, RefillFM refillEntity) {
        Optional<MaidTicket> optionalMaidTicket = maidTicketRepository.findById(id);
        MaidTicket maidTicketEntity;
        if (optionalMaidTicket.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        maidTicketEntity = optionalMaidTicket.get();

//        if (!maidTicketValidator.validate(maidTicketEntity)) {
//            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
//        }

        List<Product> availableProducts = productRepository.findAll();
        HashSet<Long> availableProductsIds = new HashSet<>();
        for (Product x : availableProducts) {
            availableProductsIds.add(x.getId());
        }

        if (!maidTicketValidator.validate(availableProductsIds, refillEntity)) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }

        for (Map.Entry<Long, Integer> entry : refillEntity.getProducts().entrySet()) {
            var product = productRepository.findById(entry.getKey());
            refillRepository.save(new Refill(maidTicketEntity, product.get(), entry.getValue()));

            var productEntity = product.get();
            productEntity.setProductAmount(productEntity.getProductAmount() - entry.getValue());
            productRepository.save(productEntity);
        }

        maidTicketEntity.setEmployee(userManager.getEmployee());
        maidTicketRepository.save(maidTicketEntity);
    }
}
