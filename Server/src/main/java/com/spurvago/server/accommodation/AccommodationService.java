package com.spurvago.server.accommodation;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import com.spurvago.database.Accommodation;
import com.spurvago.server.accommodation.models.AccommodationFM;
import com.spurvago.server.accommodation.models.AccommodationVM;
import com.spurvago.server.client.models.ClientVM;
import com.spurvago.server.room.RoomStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public record AccommodationService(AccommodationRepository accommodationRepository,
                                   AccommodationMapper accommodationMapper) {

    public AccommodationVM find(long id) {
        Accommodation entity = accommodationRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

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
        // TODO Walidacja poprawności FK client oraz FK room
        // TODO Nie powinno się dać zarezerwować pokoju z innym statusem niż
        //      dostępny
        // TODO Po rezerwacji pokoju powinien się zmieniać jego status na zarezerwowany
        Accommodation entity = accommodationMapper.mapToEntity(newEntity);
        accommodationRepository.save(entity);

        return accommodationMapper.mapToVM(entity);
    }

    public AccommodationVM update(long id, AccommodationFM newEntity) {
        // TODO Walidacja poprawności FK client oraz FK room
        // TODO Nie powinno się dać zarezerwować pokoju z innym statusem niż
        //      dostępny lub zarezerwowany, należy sprawdzać okres czasu
        //      na który jest nałożona rezerwacja
        // TODO Po rezerwacji pokoju powinien się zmieniać jego status na zarezerwowany
        Accommodation entity = accommodationRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        accommodationMapper.mapToEntity(entity, newEntity);
        accommodationRepository.save(entity);
        return accommodationMapper.mapToVM(entity);
    }

    public void delete(long id) {
        // TODO Po usunięciu rezerwacji na pokój nakładamy status do posprzątania
        //      Dodałem enumerator, aby było łatwiej, niżej test jako przykład,
        //      Pojawia się problem z tym, że rezerwacja powinna się usuwać również
        //      automatycznie co będzie prawdopodobnie wymagało ustawienia joba na
        //      dany termin. Ew temat do przemyślenia.
        int test = RoomStatus.CLEANING.intValue;

        Accommodation entity = accommodationRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        accommodationRepository.delete(entity);
    }
}
