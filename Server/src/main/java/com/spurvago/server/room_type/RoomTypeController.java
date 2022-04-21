package com.spurvago.server.room_type;

import com.spurvago.components.IBaseController;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/room_type")
public class RoomTypeController implements IBaseController<RoomType> {

    @Override
    public RoomType find(Long id) {
        return null;
    }

    @Override
    public ListPaginated<RoomType> getList(Pager pager) {
        return null;
    }

    @Override
    public RoomType create(RoomType newTestEntity) {
        return null;
    }

    @Override
    public RoomType update(Long id, RoomType newTestEntity) {
        return null;
    }

    @Override
    public void delete(Long id) {
    return null;
    }
}
