package com.spurvago.server.room_type;

import com.spurvago.components.IBaseController;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.server.client.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping(path="/api/room_type")
public class RoomTypeController implements IBaseController<RoomType> {
    private final RoomTypeService roomTypeService;

    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @Override
    public RoomType find(Long id){
        RoomType entity = roomTypeService.find(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        return entity;
    }

    @Override
    public ListPaginated<RoomType> getList(Pager pager){
        ListPaginated<RoomType> entities = roomTypeService.getList(pager);
        return entities;
    }

    @Override
    public RoomType create(RoomType newTestEntity){
        RoomType entity = roomTypeService.create(newTestEntity);
        return entity;
    }


    @Override
    public RoomType update(Long id, RoomType newTestEntity){
        RoomType entity = roomTypeService.find(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        entity = roomTypeService.update(entity, newTestEntity);

        return entity;
    }

    @Override
    public void delete(Long id) {
        RoomType entity = roomTypeService.find(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        roomTypeService.delete(entity);
    }

    @GetMapping(path = "/name")
    public ListPaginated<RoomType> getFiltered(@RequestParam String input, Pager pager) {
        return roomTypeService.getFiltered(input, pager);
    }
}
