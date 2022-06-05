package com.spurvago.server.maid_ticket;


import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.server.maid_ticket.models.MaidTicketFM;
import com.spurvago.server.maid_ticket.models.MaidTicketVM;
import com.spurvago.server.maid_ticket.models.RefillFM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/maid_ticket")
public class MaidTicketController {
    private final MaidTicketService maidTicketService;

    @Autowired
    public MaidTicketController(MaidTicketService maidService) {
        this.maidTicketService = maidService;
    }

    //<editor-fold desc="find()">
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MaidTicketVM find(@PathVariable Long id) {
        return maidTicketService.find(id);
    }
    //</editor-fold>

    //<editor-fold desc="getList()">
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ListPaginated<MaidTicketVM> getList(Pager pager, String search) {
        return maidTicketService.getList(pager, search);
    }
    //</editor-fold>

    //<editor-fold desc="create()">
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public MaidTicketVM create(@RequestBody MaidTicketFM newEntity) {
        return maidTicketService.create(newEntity);
    }
    //</editor-fold>

    //<editor-fold desc="update()">
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MaidTicketVM update(@PathVariable Long id, @RequestBody MaidTicketFM newEntity) {
        return maidTicketService.update(id, newEntity);
    }
    //</editor-fold>

    //<editor-fold desc="delete()">
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        maidTicketService.delete(id);
    }
    //</editor-fold>

    //<editor-fold desc="refill()">
    @PostMapping(path = "/{id}/refill")
    @ResponseStatus(HttpStatus.CREATED)
    public void refill(@PathVariable Long id, @RequestBody RefillFM refillEntity) {
        maidTicketService.refill(id, refillEntity);
    }
    //</editor-fold>

    // TODO Dorobić endpoint na dodawanie zlecenia sprzątania przez recepcjonistę
    // TODO Dorobić endpoint pozwalający na zakańczanie zlecenia przez pokojówkę
}
