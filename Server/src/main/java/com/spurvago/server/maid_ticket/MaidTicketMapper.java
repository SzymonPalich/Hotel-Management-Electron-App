package com.spurvago.server.maid_ticket;

import com.spurvago.database.MaidTicket;
import com.spurvago.server.employee.EmployeeRepository;
import com.spurvago.server.maid_ticket.models.MaidTicketFM;
import com.spurvago.server.maid_ticket.models.MaidTicketVM;
import com.spurvago.server.room.RoomRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public record MaidTicketMapper(EmployeeRepository employeeRepository, RoomRepository roomRepository) {
    MaidTicket mapToEntity(MaidTicketFM src) {
        MaidTicket dest = new MaidTicket();
        dest.setRoom(roomRepository.findById(src.getRoomId()));
        if (src.getEmployeeId() != null) {
            dest.setEmployee(employeeRepository.findById(src.getEmployeeId()).orElse(null));
        }
        dest.setFinalizationDate(src.getFinalizationDate());

        return dest;
    }

    void mapToEntity(MaidTicket dest, MaidTicketFM src) {
        dest.setRoom(roomRepository.findById(src.getRoomId()));
        if (src.getEmployeeId() != null) {
            dest.setEmployee(employeeRepository.findById(src.getEmployeeId()).orElse(null));
        }
        dest.setFinalizationDate(src.getFinalizationDate());

    }

    MaidTicketVM mapToVM(MaidTicket src) {
        MaidTicketVM dest = new MaidTicketVM();

        dest.setId(src.getId());
        dest.setRoomId(src.getRoom().getId());
        dest.setRoomNumber(src.getRoom().getRoomNumber());
        dest.setRoomType(src.getRoom().getRoomType().getType());
        dest.setRoomStatus(src.getRoom().getStatus());
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
