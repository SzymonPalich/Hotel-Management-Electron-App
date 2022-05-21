package com.spurvago.server.room;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import com.spurvago.database.Room;
import com.spurvago.server.room.models.RoomFM;
import com.spurvago.server.room.models.RoomVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public record RoomService(RoomRepository roomRepository, RoomMapper roomMapper) {

    public RoomVM find(long id) {
        Room entity = roomRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        return roomMapper.mapToVM(entity);
    }

    public ListPaginated<RoomVM> getList(Pager pager, String search) {
        Pageable pageable = pager.makePageable();
        Page<Room> entities;
        if (Utils.isNullOrBlank(search)) {
            entities = roomRepository.findAll(pageable);
        } else {
            List<String> words = List.of(search.split("\\s"));
            Specification<Room> specification = RoomRepository.search(words);
            entities = roomRepository.findAll(specification, pageable);
        }

        List<RoomVM> entitiesDTO = roomMapper.mapToList(entities.getContent());
        return new ListPaginated<>(entitiesDTO, pager,
                entities.getTotalElements(), entities.getTotalPages());
    }

    public RoomVM create(RoomFM newEntity) {
        // TODO Validacja
        Room entity = roomMapper.mapToEntity(newEntity);
        roomRepository.save(entity);
        return roomMapper.mapToVM(entity);
    }

    public RoomVM update(long id, RoomFM newEntity) {
        // TODO Validacja
        Room entity = roomRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        roomMapper.mapToEntity(entity, newEntity);
        roomRepository.save(entity);
        return roomMapper.mapToVM(entity);
    }

    public void delete(long id) {
        Room entity = roomRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        roomRepository.delete(entity);
    }
}
