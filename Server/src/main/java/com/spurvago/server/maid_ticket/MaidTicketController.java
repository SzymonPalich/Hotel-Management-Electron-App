package com.spurvago.server.maid_ticket;


import com.spurvago.database.MaidTicket;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.server.maid_ticket.models.MaidTicketFM;
import com.spurvago.server.maid_ticket.models.MaidTicketVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping(path = "/api/maid_ticket")
public class MaidTicketController {
    private final MaidTicketService maidTicketService;

    @Autowired
    public MaidTicketController(MaidTicketService maidService) {
        this.maidTicketService = maidService;
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MaidTicketVM find(@PathVariable Long id) {
        return maidTicketService.find(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ListPaginated<MaidTicketVM> getList(Pager pager, String search) {
        return maidTicketService.getList(pager, search);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public MaidTicketVM create(MaidTicketFM newEntity) {
        return maidTicketService.create(newEntity);
    }
/*
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MaidTicket update(Long id, MaidTicket newEntity) {
        MaidTicket entity = maidTicketService.find(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        return entity;
    }*/

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
         maidTicketService.delete(id);
    }

/*    @GetMapping(path = "/name")
    public ListPaginated<MaidTicket> getFiltered(@RequestParam String input, Pager pager) {
        return maidTicketService.getFiltered(input, pager);
    }*/
}
