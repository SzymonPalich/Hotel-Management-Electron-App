package com.spurvago.server.accommodation;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import com.spurvago.database.Accommodation;
import com.spurvago.server.accommodation.models.AccommodationFM;
import com.spurvago.server.accommodation.models.AccommodationVM;
import com.spurvago.server.maid_ticket.models.MaidTicketVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
@Service
public record AccommodationService(AccommodationRepository accommodationRepository, AccommodationMapper accommodationMapper) {
    public AccommodationVM find(long id) {
        var entity = accommodationRepository.findById(id);
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

        Page<AccommodationVM> entitiesDTO = entities.map(AccommodationVM::new);

        return new ListPaginated<>(entitiesDTO, pager);
    }

    public AccommodationVM create(AccommodationFM newEntity) {
        var entity = accommodationMapper.mapToEntity(newEntity);
        accommodationRepository.save(entity);
        return accommodationMapper.mapToVM(entity);
    }

    public AccommodationVM update(long id, AccommodationFM newEntity) {
        Accommodation entity = accommodationRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        accommodationMapper.mapToEntity(entity, newEntity);
        accommodationRepository.save(entity);
        return new AccommodationVM(entity);
    }

    public void delete(long id) {
        var entity = accommodationRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        accommodationRepository.delete(entity);
    }
}
