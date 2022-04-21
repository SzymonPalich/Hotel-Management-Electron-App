package com.spurvago.server.maid_ticket;

import com.spurvago.components.IBaseController;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping(path = "/api/maid_ticket")
public class MaidController implements IBaseController<MaidTicket> {
    private final MaidService maidService;

    @Autowired
    public MaidController(MaidService maidService) {
        this.maidService = maidService;
    }

    @Override
    public MaidTicket find(Long id) {
        MaidTicket entity = maidService.find(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        return entity;
    }

    @Override
    public ListPaginated<MaidTicket> getList(Pager pager) {
        return maidService.getList(pager);
    }

    @Override
    public MaidTicket create(MaidTicket newEntity) {
        return maidService.create(newEntity);
    }

    @Override
    public MaidTicket update(Long id, MaidTicket newEntity) {
        MaidTicket entity = maidService.find(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        return entity;
    }

    @Override
    public void delete(Long id) {
         MaidTicket entity = maidService.find(id);
         if (entity == null) {
             throw new ResponseStatusException(NOT_FOUND);
         }

         maidService.delete(entity);
    }
}
