package com.spurvago.server.accommodation;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import com.spurvago.database.Accommodation;
import com.spurvago.database.Room;
import com.spurvago.server.accommodation.models.AccommodationFM;
import com.spurvago.server.accommodation.models.AccommodationVM;
import com.spurvago.server.room.RoomRepository;
import com.spurvago.server.room.RoomStatus;
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
public record AccommodationService(AccommodationRepository accommodationRepository,
                                   AccommodationMapper accommodationMapper,
                                   AccommodationValidator accommodationValidator,
                                   RoomRepository roomRepository) {

    public AccommodationVM find(Long id) {
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(id);
        Accommodation entity;
        if (optionalAccommodation.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalAccommodation.get();

        return accommodationMapper.mapToVM(entity);
    }

    public ListPaginated<AccommodationVM> getList(Pager pager, String search) {
        Pageable pageable = pager.makePageable();
        Page<Accommodation> entities;

        if (Utils.isNullOrBlank(search)) {
            entities = accommodationRepository.findAll(pageable);
        } else {
            List<String> words = List.of(search.split("\\s"));
            Specification<Accommodation> specification = AccommodationRepository.search(words);
            entities = accommodationRepository.findAll(specification, pageable);
        }

        List<AccommodationVM> entitiesDTO = accommodationMapper.mapToList(entities.getContent());
        return new ListPaginated<>(entitiesDTO, pager,
                entities.getTotalElements(), entities.getTotalPages());
    }

    public AccommodationVM create(AccommodationFM newEntity) {
        // TODO Walidacja poprawności FK client oraz FK room -- Done I Guess
        if (!accommodationValidator().validate(newEntity)) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }
        Optional<Room> temp = roomRepository.findById(newEntity.getRoomId());
        Room room;
        if (temp.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        room = temp.get();
        room.setStatus(RoomStatus.CLEANING.intValue);
        roomRepository.save(room);

        Accommodation entity = accommodationMapper.mapToEntity(newEntity);
        accommodationRepository.save(entity);

        return accommodationMapper.mapToVM(entity);
    }

    public AccommodationVM update(Long id, AccommodationFM newEntity) {
        // TODO Walidacja poprawności FK client oraz FK room - Done I Guess
        // TODO Nie powinno się dać zarezerwować pokoju z innym statusem niż
        //      dostępny lub zarezerwowany, należy sprawdzać okres czasu
        //      na który jest nałożona rezerwacja
        // TODO Po rezerwacji pokoju powinien się zmieniać jego status na zarezerwowany
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(id);
        Accommodation entity;
        if (optionalAccommodation.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalAccommodation.get();

        accommodationMapper.mapToEntity(entity, newEntity);
        accommodationRepository.save(entity);
        return accommodationMapper.mapToVM(entity);
    }

    public void delete(Long id) {
        // TODO Po usunięciu rezerwacji na pokój nakładamy status do posprzątania
        //      Dodałem enumerator, aby było łatwiej, niżej test jako przykład,
        //      Pojawia się problem z tym, że rezerwacja powinna się usuwać również
        //      automatycznie co będzie prawdopodobnie wymagało ustawienia joba na
        //      dany termin. Ew temat do przemyślenia.
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(id);
        Accommodation entity;
        if (optionalAccommodation.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalAccommodation.get();

        Optional<Room> temp = roomRepository.findById(entity.getRoom().getId());
        Room room;
        if (temp.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        room = temp.get();
        room.setStatus(RoomStatus.CLEANING.intValue);
        roomRepository.save(room);

        accommodationRepository.delete(entity);
    }
}
