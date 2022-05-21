package com.spurvago.server.maintenance_ticket;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.server.maintenance_ticket.models.MaintenanceTicketFM;
import com.spurvago.server.maintenance_ticket.models.MaintenanceTicketVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/maintenance_ticket")
public class MaintenanceTicketController {
    private final MaintenanceTicketService maintenanceTicketService;

    @Autowired
    public MaintenanceTicketController(MaintenanceTicketService maintenanceTicketService) {
        this.maintenanceTicketService = maintenanceTicketService;
    }

    //<editor-fold desc="find()">
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MaintenanceTicketVM find(@PathVariable long id) {
        return maintenanceTicketService.find(id);
    }
    //</editor-fold>

    //<editor-fold desc="getList()">
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ListPaginated<MaintenanceTicketVM> getList(Pager pager, String search) {
        return maintenanceTicketService.getList(pager, search);
    }
    //</editor-fold>

    //<editor-fold desc="create()">
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceTicketVM create(MaintenanceTicketFM newEntity) {
        return maintenanceTicketService.create(newEntity);
    }
    //</editor-fold>

    //<editor-fold desc="update()">
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MaintenanceTicketVM update(@PathVariable long id, @RequestBody MaintenanceTicketFM newEntity) {
        return maintenanceTicketService.update(id, newEntity);
    }
    //</editor-fold>

    //<editor-fold desc="delete()">
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        maintenanceTicketService.delete(id);
    }
    //</editor-fold>

    // TODO Dorobić endpoint na dodawanie zlecenia dla technika przez recepcjonistę
    // TODO Dorobić endpoint pozwalający na zakańczanie zlecenia przez technika
}
