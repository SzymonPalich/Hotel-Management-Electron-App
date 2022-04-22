package com.spurvago.server.room_type;

import com.spurvago.components.IBaseService;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public record RoomTypeService(RoomTypeRepository roomTypeRepository) implements IBaseService<RoomType> {

    @Override
    public RoomType find(long id) {
        return roomTypeRepository.findById(id);
    }

    @Override
    public ListPaginated<RoomType> getList(Pager pager) {
        Pageable pageable = pager.makePageable();
        Page<RoomType> entities = roomTypeRepository.findAll(pageable);
        ListPaginated<RoomType> listPaginated = new ListPaginated<>(entities, pager);
        return listPaginated;
    }

    @Override
    public RoomType create(RoomType newEntity) {
        return roomTypeRepository.save(newEntity);
    }

    @Override
    public RoomType update(RoomType oldEntity, RoomType newEntity) {
        oldEntity.map(newEntity);

        return roomTypeRepository.save(oldEntity);
    }

    @Override
    public void delete(RoomType Entity)  {
        roomTypeRepository.delete(Entity);
    }
}
