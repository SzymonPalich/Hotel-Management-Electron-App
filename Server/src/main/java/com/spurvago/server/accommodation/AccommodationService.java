package com.spurvago.server.accommodation;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import com.spurvago.database.Accommodation;
import com.spurvago.database.MaidTicket;
import com.spurvago.database.Refill;
import com.spurvago.database.Room;
import com.spurvago.server.accommodation.models.AccommodationFM;
import com.spurvago.server.accommodation.models.AccommodationVM;
import com.spurvago.server.maid_ticket.MaidTicketRepository;
import com.spurvago.server.maid_ticket.MaidTicketService;
import com.spurvago.server.product.ProductRepository;
import com.spurvago.server.refill.RefillRepository;
import com.spurvago.server.room.RoomRepository;
import com.spurvago.server.room.RoomStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.spurvago.InvoiceGenerator.InvoiceGenerator;
import com.spurvago.InvoiceGenerator.InvoiceDetails;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Ref;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

/**
 * Serwis rezerwacji
 * @param accommodationRepository
 * @param accommodationMapper
 * @param accommodationValidator
 * @param roomRepository
 */
@Service
public record AccommodationService(AccommodationRepository accommodationRepository,
                                   AccommodationMapper accommodationMapper,
                                   AccommodationValidator accommodationValidator,
                                   RoomRepository roomRepository,
                                   MaidTicketRepository maidTicketRepository,
                                   ProductRepository productRepository,
                                   RefillRepository refillRepository) {

    public AccommodationVM find(Long id) {
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(id);
        Accommodation entity;
        if (optionalAccommodation.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalAccommodation.get();

        return accommodationMapper.mapToVM(entity);
    }

    /**
     * Pobieranie listy rezerwacji wraz z wyszukiwaniem
     * @param pager
     * @param search
     * @return
     */
    public ListPaginated<AccommodationVM> getList(Pager pager, String search) {
        pager.sort = "startDate";
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

    /**
     * Utworznie rezerwacji po walidacji wprowadzonych danych
     * @param newEntity
     * @return
     */
    public AccommodationVM create(AccommodationFM newEntity) {
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
        entity = accommodationRepository.save(entity);


        var maidTicket = new MaidTicket();
        maidTicket.setAccommodation(entity);
        maidTicket.setFinalizationDate(newEntity.getEndDate());
        maidTicketRepository.save(maidTicket);

        return accommodationMapper.mapToVM(entity);
    }

    /**
     * Aktualizacja rezerwacji po walidacji wprowadzonych danych
     * @param id
     * @param newEntity
     * @return
     */
    public AccommodationVM update(Long id, AccommodationFM newEntity) {
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

    /**
     * Usunięcie rezerwacji
     * @param id
     */
    public void delete(Long id) {
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

    /**
     * Generowanie faktury
     * @param id
     */
    public void generateInvoice(Long id) {
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(id);
        Accommodation entity;
        if (optionalAccommodation.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalAccommodation.get();

        LocalDate start = entity.getStartDate().toLocalDate();
        LocalDate end = entity.getEndDate().toLocalDate();
        int days = Math.abs((int) ChronoUnit.DAYS.between(end, start));
        var minibar = getProductsPrice(entity);
        InvoiceGenerator inv = new InvoiceGenerator();
        InvoiceDetails invoiceDetails = new InvoiceDetails(
                entity.getClient().getFirstName() + " " + entity.getClient().getLastName(),
                entity.getRoom().getRoomNumber() + " " + entity.getRoom().getRoomType().getType(),
                days, entity.getStartDate(), entity.getEndDate(),
                entity.getRoom().getRoomType().getPrice(), minibar
        );

        try {
            inv.generatePDF(invoiceDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BigDecimal getProductsPrice(Accommodation accommodation) {
        MaidTicket maidTicket = maidTicketRepository.findByAccommodation(accommodation).get();
        if (maidTicket.getEmployee() == null)
            return BigDecimal.ZERO;

        BigDecimal sum = BigDecimal.ZERO;
        List<Refill> refills = refillRepository.getRefillByMaidTicket(maidTicket);
        for (Refill refill : refills) {
            var amount = refill.getAmount();
            var price = refill.getProduct().getWholesalePrice();
            sum = sum.add(price.multiply(BigDecimal.valueOf(amount)));
        }
        return sum;
    }
}
