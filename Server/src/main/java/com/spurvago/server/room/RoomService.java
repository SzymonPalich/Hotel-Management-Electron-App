package com.spurvago.server.room;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import com.spurvago.database.Accommodation;
import com.spurvago.database.Room;
import com.spurvago.server.accommodation.AccommodationRepository;
import com.spurvago.server.room.models.RoomFM;
import com.spurvago.server.room.models.RoomSelect;
import com.spurvago.server.room.models.RoomVM;
import com.spurvago.server.room_type.RoomTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public record RoomService(RoomRepository roomRepository,
                          RoomTypeRepository roomTypeRepository,
                          AccommodationRepository accommodationRepository,
                          RoomMapper roomMapper,
                          RoomValidator roomValidator) {

    public RoomVM find(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        Room entity;
        if (optionalRoom.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalRoom.get();

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

    public List<RoomSelect> getSelectList() {
        List<Room> entities = roomRepository.findAll();

        List<RoomSelect> entitiesDTO = roomMapper.mapToSelectList(entities);
        return entitiesDTO;
    }

    public List<RoomSelect> getAvailableList(Date startDate, Date endDate, long roomTypeId) {
        var roomType = roomTypeRepository.findById(roomTypeId);
        if (roomType.isEmpty()) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }

        List<Room> entities = roomRepository.findAllByRoomType(roomType.get());
        List<Room> available = new ArrayList<>();

        List<Accommodation> accommodations;
        for (var entity: entities) {
            boolean isAvailable = true;
            accommodations = accommodationRepository.findAllByRoom(entity);
            for (var accommodation: accommodations) {
                if (!checkAvailability(accommodation.getStartDate(),
                        accommodation.getEndDate(), startDate, endDate)) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) {
                available.add(entity);
            }
            if (available.size() == 5)
                break;
        }

        List<RoomSelect> entitiesDTO = roomMapper.mapToSelectList(available);
        return entitiesDTO;
    }

    public RoomVM create(RoomFM newEntity) {
        if (!roomValidator().validate(newEntity)) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }

        Room entity = roomMapper.mapToEntity(newEntity);
        roomRepository.save(entity);
        return roomMapper.mapToVM(entity);
    }

    public RoomVM update(Long id, RoomFM newEntity) {
        if (!roomValidator().validate(newEntity)) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }

        Optional<Room> optionalRoom = roomRepository.findById(id);
        Room entity;
        if (optionalRoom.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalRoom.get();

        roomMapper.mapToEntity(entity, newEntity);
        roomRepository.save(entity);
        return roomMapper.mapToVM(entity);
    }

    public void delete(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        Room entity;
        if (optionalRoom.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalRoom.get();

        roomRepository.delete(entity);
    }

    private boolean checkAvailability(Date startDate, Date endDate,
                                      Date targetStartDate, Date targetEndDate) {
        long nStartDate = startDate.getTime();
        long nEndDate = endDate.getTime();
        long nTargetStartDate = targetStartDate.getTime();
        long nTargetEndDate = targetEndDate.getTime();

        if (nTargetStartDate <= nStartDate && nTargetEndDate >= nEndDate) {
            return false;
        }

        if (nTargetStartDate >= nStartDate && nTargetStartDate <= nEndDate) {
            return false;
        }

        if (nTargetEndDate >= nStartDate && nTargetEndDate <= nEndDate) {
            return false;
        }

        return true;
//        if (tpmStartDate.before(accommodation.getStartDate())
//                && tpmEndDate.after(accommodation.getEndDate())) {
//            isAvailable = false;
//            break;
//        }
//        if (startDate.after(accommodation.getStartDate())
//                && startDate.before(accommodation.getEndDate())) {
//            isAvailable = false;
//            break;
//        }
//        if (endDate.after(accommodation.getStartDate())
//                && endDate.before(accommodation.getEndDate())) {
//            isAvailable = false;
//            break;
//        }

    }
}
