package com.spurvago.server.maid_ticket;

import com.spurvago.database.MaidTicket;
import com.spurvago.server.employee.EmployeeRepository;
import com.spurvago.server.maid_ticket.models.MaidTicketFM;
import com.spurvago.server.maid_ticket.models.MaidTicketVM;
import org.springframework.stereotype.Component;

@Component
public record MaidTicketMapper(EmployeeRepository employeeRepository) {
    MaidTicket mapToEntity(MaidTicketFM src) {
        MaidTicket dest = new MaidTicket();
        dest.setRoomId(src.getRoomId());
        dest.setEmployee(employeeRepository.findById(src.getEmployeeId()));
        dest.setFinalizationDate(src.getFinalizationDate());

        return dest;
    }

    MaidTicket mapToEntity(MaidTicket dest, MaidTicketFM src) {
        dest.setRoomId(src.getRoomId());
        dest.setEmployee(employeeRepository.findById(src.getEmployeeId()));
        dest.setFinalizationDate(src.getFinalizationDate());

        return dest;
    }

    MaidTicketVM mapToVM(MaidTicket src) {
        MaidTicketVM dest = new MaidTicketVM();

        dest.setId(src.getId());
        dest.setEmployeeId(src.getId());
        dest.setRoomId(src.getRoomId());
        dest.setFinalizationDate(src.getFinalizationDate());
        dest.setEmployeeId(src.getEmployee().getId());
        dest.setEmployeeFirstName(src.getEmployee().getFirstName());
        dest.setEmployeeLastName(src.getEmployee().getLastName());

        return dest;
    }
}
