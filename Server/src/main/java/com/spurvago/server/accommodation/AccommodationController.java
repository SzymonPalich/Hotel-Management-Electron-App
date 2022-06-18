package com.spurvago.server.accommodation;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.server.accommodation.models.AccommodationFM;
import com.spurvago.server.accommodation.models.AccommodationVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Kontroler rezerwacji
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/accommodation")
public class AccommodationController {
    private final AccommodationService accommodationService;

    @Autowired
    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    //<editor-fold desc="find()">
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccommodationVM find(@PathVariable Long id) {
        return accommodationService.find(id);
    }
    //</editor-fold>

    //<editor-fold desc="getList()">
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ListPaginated<AccommodationVM> getList(Pager pager, String search) {
        return accommodationService.getList(pager, search);
    }
    //</editor-fold>

    //<editor-fold desc="create()">
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AccommodationVM create(@RequestBody AccommodationFM newEntity) {
        return accommodationService.create(newEntity);
    }
    //</editor-fold>

    //<editor-fold desc="update()">
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccommodationVM update(@PathVariable Long id, @RequestBody AccommodationFM newEntity) {
        return accommodationService.update(id, newEntity);
    }
    //</editor-fold>

    //<editor-fold desc="delete()">
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        accommodationService.delete(id);
    }
    //</editor-fold>

//    <editor-fold desc="invoice()">
//    @PutMapping(path = "/{id}/invoice")
//    @ResponseStatus(HttpStatus.OK)
//    public void generateInvoice(@PathVariable Long id) {
//        accommodationService.generateInvoice(id);
//    }
//    </editor-fold>

    @GetMapping(path = "/{id}/temp")
    @ResponseStatus(HttpStatus.OK)
    public void temp(@PathVariable Long id) {
        accommodationService.temp(id);
    }
}
