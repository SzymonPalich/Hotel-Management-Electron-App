package com.spurvago.server.room_type;

import com.spurvago.components.IBaseService;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RoomTypeService(RoomTypeRepository roomTypeRepository) implements IBaseService<RoomType> {

    @Override
    public RoomType find(long id) {
        return roomTypeRepository.findById(id);
    }

    @Override
    public ListPaginated<RoomType> getList(Pager pager, String search) {
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
    public void delete(RoomType Entity) {
        roomTypeRepository.delete(Entity);
    }

    public ListPaginated<RoomType> getFiltered(String input, Pager pager) {
        Pageable pageable = pager.makePageable();
        if(input.isEmpty()) return null;
        List<String> words = List.of(input.split("\\s"));
        Specification<RoomType> specification = RoomTypeRepository.search(words);
        Page<RoomType> entities = roomTypeRepository.findAll(specification, pageable);
        return new ListPaginated<>(entities, pager);
    }
}
