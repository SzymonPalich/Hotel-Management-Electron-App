package com.spurvago.server.maid_ticket;

import com.spurvago.database.MaidTicket;
import com.spurvago.server.employee.EmployeeRepository;
import com.spurvago.server.maid_ticket.models.MaidTicketFM;
import com.spurvago.server.maid_ticket.models.MaidTicketVM;
import com.spurvago.server.room.RoomRepository;
import org.springframework.stereotype.Component;

@Component
public record MaidTicketMapper(EmployeeRepository employeeRepository, RoomRepository roomRepository) {
    MaidTicket mapToEntity(MaidTicketFM src) {
        MaidTicket dest = new MaidTicket();
        dest.setRoom(roomRepository.findById(src.getRoomId()));
        dest.setEmployee(employeeRepository.findById(src.getEmployeeId()));
        dest.setFinalizationDate(src.getFinalizationDate());

        return dest;
    }

    MaidTicket mapToEntity(MaidTicket dest, MaidTicketFM src) {
        dest.setRoom(roomRepository.findById(src.getRoomId()));
        dest.setEmployee(employeeRepository.findById(src.getEmployeeId()));
        dest.setFinalizationDate(src.getFinalizationDate());

        return dest;
    }

    MaidTicketVM mapToVM(MaidTicket src) {
        MaidTicketVM dest = new MaidTicketVM();

        dest.setId(src.getId());
        dest.setRoomId(src.getRoom().getId());
        dest.setRoomNumber(src.getRoom().getRoomNumber());
        dest.setRoomType(src.getRoom().getRoomType().getType());
        dest.setRoomStatus(src.getRoom().getStatus());
        dest.setFinalizationDate(src.getFinalizationDate());
        dest.setEmployeeId(src.getEmployee().getId());
        dest.setEmployeeFirstName(src.getEmployee().getFirstName());
        dest.setEmployeeLastName(src.getEmployee().getLastName());

        return dest;
    }
}
