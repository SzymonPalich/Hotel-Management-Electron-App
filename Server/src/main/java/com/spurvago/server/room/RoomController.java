package com.spurvago.server.room;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.server.room.models.RoomFM;
import com.spurvago.server.room.models.RoomSelect;
import com.spurvago.server.room.models.RoomVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/room")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    //<editor-fold desc="find()">
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomVM find(@PathVariable Long id) {
        return roomService.find(id);
    }
    //</editor-fold>

    //<editor-fold desc="getList()">
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ListPaginated<RoomVM> getList(Pager pager, String search) {
        return roomService.getList(pager, search);
    }
    //</editor-fold>

    //<editor-fold desc="getSelectList()">
    @GetMapping(path = "/select-list")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomSelect> getSelectList() {
        return roomService.getSelectList();
    }
    //</editor-fold>

    //<editor-fold desc="getAvailableList()">
    @GetMapping(path = "/available")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomSelect> getAvailableList(Date startDate, Date endDate, long roomTypeId) {
        return roomService.getAvailableList(startDate, endDate, roomTypeId);
    }
    //</editor-fold>

    //<editor-fold desc="create()">
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RoomVM create(@RequestBody RoomFM newEntity) {
        return roomService.create(newEntity);
    }
    //</editor-fold>

    //<editor-fold desc="update()">
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomVM update(@PathVariable Long id, @RequestBody RoomFM newEntity) {
        return roomService.update(id, newEntity);
    }
    //</editor-fold>

    //<editor-fold desc="delete()">
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        roomService.delete(id);
    }
    //</editor-fold>
}
