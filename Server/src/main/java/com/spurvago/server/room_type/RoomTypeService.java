package com.spurvago.server.room_type;

import com.spurvago.components.IBaseService;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import com.spurvago.database.RoomType;
import com.spurvago.server.product.models.ProductVM;
import com.spurvago.server.room_type.models.RoomTypeVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public record RoomTypeService(RoomTypeRepository roomTypeRepository, RoomTypeMapper roomTypeMapper) {

    public RoomTypeVM find(long id) {
        var entity = roomTypeRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

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

        Page<RoomTypeVM> entitiesDTO = entities.map(RoomTypeVM::new);

        return new ListPaginated<>(entitiesDTO, pager);
    }

    public RoomTypeVM create(RoomType newEntity) {
        roomTypeRepository.save(newEntity);
        return roomTypeMapper.mapToVM(newEntity);
    }

    public RoomTypeVM update(long id, RoomType newEntity) {
        RoomType entity = roomTypeRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        roomTypeMapper.mapToEntity(entity, newEntity);
        roomTypeRepository.save(entity);
        return new RoomTypeVM(entity);
    }

    public void delete(long id) {
        var entity = roomTypeRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        roomTypeRepository.delete(entity);
    }
}
