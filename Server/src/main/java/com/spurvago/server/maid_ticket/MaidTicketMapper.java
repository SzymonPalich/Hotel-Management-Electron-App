package com.spurvago.server.maid_ticket;

import com.spurvago.database.MaidTicket;
import com.spurvago.server.employee.EmployeeRepository;
import com.spurvago.server.maid_ticket.models.MaidTicketFM;
import com.spurvago.server.maid_ticket.models.MaidTicketVM;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public record MaidTicketMapper(EmployeeRepository employeeRepository) {
    //<editor-fold desc="MaidTicketFM -> MaidTicket">
    public MaidTicket map(MaidTicketFM formModel, MaidTicket entity) {
        entity.setRoomId(formModel.getRoomId());
        entity.setEmployee(employeeRepository.findById(formModel.getEmployeeId()));
        entity.setFinalizationDate(formModel.getFinalizationDate());
        return entity;
    }
    //</editor-fold>

    //<editor-fold desc="MaidTicket -> MaidTicketVM">
    public MaidTicketVM map(MaidTicket entity, MaidTicketVM viewModel) {
        viewModel.setId(entity.getId());
        viewModel.setRoomId(entity.getRoomId());
        viewModel.setEmployeeId(entity.getEmployee().getId());
        viewModel.setEmployeeFirstName(entity.getEmployee().getFirstName());
        viewModel.setEmployeeLastName(entity.getEmployee().getLastName());
        viewModel.setFinalizationDate(entity.getFinalizationDate());
        return new MaidTicketVM();
    }
    //</editor-fold>
}
