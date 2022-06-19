package com.spurvago.server.maid_ticket;

import com.spurvago.database.Accommodation;
import com.spurvago.database.MaidTicket;
import com.spurvago.database.Room;
import com.spurvago.server.accommodation.AccommodationRepository;
import com.spurvago.server.employee.EmployeeRepository;
import com.spurvago.server.maid_ticket.models.MaidTicketFM;
import com.spurvago.server.maid_ticket.models.MaidTicketVM;
import com.spurvago.server.room.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Mapper zgłoszeń sprzątania
 * @param employeeRepository
 * @param roomRepository
 */
@Component
public record MaidTicketMapper(EmployeeRepository employeeRepository, RoomRepository roomRepository, AccommodationRepository accommodationRepository) {
    MaidTicket mapToEntity(MaidTicketFM src) {
        MaidTicket dest = new MaidTicket();
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(src.getAccommodationId());
        Accommodation accommodation;
        if (optionalAccommodation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        accommodation = optionalAccommodation.get();

        dest.setAccommodation(accommodation);
        if (src.getEmployeeId() != null) {
            dest.setEmployee(employeeRepository.findById(src.getEmployeeId()).orElse(null));
        } else {
            dest.setEmployee(null);
        }
        dest.setFinalizationDate(src.getFinalizationDate());

        return dest;
    }

    void mapToEntity(MaidTicket dest, MaidTicketFM src) {
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(src.getAccommodationId());
        Accommodation accommodation;
        if (optionalAccommodation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        accommodation = optionalAccommodation.get();

        dest.setAccommodation(accommodation);
        if (src.getEmployeeId() != null) {
            dest.setEmployee(employeeRepository.findById(src.getEmployeeId()).orElse(null));
        }
        dest.setFinalizationDate(src.getFinalizationDate());

    }

    MaidTicketVM mapToVM(MaidTicket src) {
        MaidTicketVM dest = new MaidTicketVM();

        dest.setId(src.getId());
        dest.setAccommodationId(src.getAccommodation().getId());
        dest.setRoomNumber(src.getAccommodation().getRoom().getRoomNumber());
        dest.setRoomType(src.getAccommodation().getRoom().getRoomType().getType());
        dest.setRoomStatus(src.getAccommodation().getRoom().getStatus());
        dest.setFinalizationDate(src.getFinalizationDate());
        if (src.getEmployee() != null) {
            dest.setEmployeeId(src.getEmployee().getId());
            dest.setEmployeeFirstName(src.getEmployee().getFirstName());
            dest.setEmployeeLastName(src.getEmployee().getLastName());
        }

        return dest;
    }

    List<MaidTicketVM> mapToList(List<MaidTicket> srcList) {
        List<MaidTicketVM> destList = new ArrayList<>();
        for (MaidTicket srcEntity : srcList) {
            destList.add(mapToVM(srcEntity));
        }

        return destList;
    }
}
