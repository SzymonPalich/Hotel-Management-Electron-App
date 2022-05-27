package com.spurvago.server.room_type;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import com.spurvago.database.RoomType;
import com.spurvago.server.room_type.models.RoomTypeFM;
import com.spurvago.server.room_type.models.RoomTypeVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public record RoomTypeService(RoomTypeRepository roomTypeRepository,
                              RoomTypeMapper roomTypeMapper,
                              RoomTypeValidator roomTypeValidator) {

    public RoomTypeVM find(Long id) {
        Optional<RoomType> optionalRoomType = roomTypeRepository.findById(id);
        RoomType entity;
        if (optionalRoomType.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalRoomType.get();

        return roomTypeMapper.mapToVM(entity);
    }


    public ListPaginated<RoomTypeVM> getList(Pager pager, String search) {
        Pageable pageable = pager.makePageable();
        Page<RoomType> entities;
        if (Utils.isNullOrBlank(search)) {
            entities = roomTypeRepository.findAll(pageable);
        } else {
            List<String> words = List.of(search.split("\\s"));
            Specification<RoomType> specification = RoomTypeRepository.search(words);
            entities = roomTypeRepository.findAll(specification, pageable);
        }

        List<RoomTypeVM> entitiesDTO = roomTypeMapper.mapToList(entities.getContent());
        return new ListPaginated<>(entitiesDTO, pager,
                entities.getTotalElements(), entities.getTotalPages());
    }

    public RoomTypeVM create(RoomTypeFM newEntity) {
        if (!roomTypeValidator().validate(newEntity)) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }

        RoomType entity = roomTypeMapper.mapToEntity(newEntity);
        roomTypeRepository.save(entity);
        return roomTypeMapper.mapToVM(entity);
    }

    public RoomTypeVM update(Long id, RoomTypeFM newEntity) {
        Optional<RoomType> optionalRoomType = roomTypeRepository.findById(id);
        RoomType entity;
        if (optionalRoomType.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        if (!roomTypeValidator().validate(newEntity)) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }

        entity = optionalRoomType.get();

        roomTypeMapper.mapToEntity(entity, newEntity);
        roomTypeRepository.save(entity);
        return roomTypeMapper.mapToVM(entity);
    }

    public void delete(Long id) {
        Optional<RoomType> optionalRoomType = roomTypeRepository.findById(id);
        RoomType entity;
        if (optionalRoomType.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalRoomType.get();

        roomTypeRepository.delete(entity);
    }
}
