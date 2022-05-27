package com.spurvago.server.maid_ticket;

import com.spurvago.components.Validator;
import com.spurvago.server.employee.EmployeeRepository;
import com.spurvago.server.maid_ticket.models.MaidTicketFM;
import com.spurvago.server.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MaidTicketValidator extends Validator {
    private final EmployeeRepository employeeRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public MaidTicketValidator(EmployeeRepository employeeRepository, RoomRepository roomRepository) {
        this.employeeRepository = employeeRepository;
        this.roomRepository = roomRepository;
    }

    public boolean validate(MaidTicketFM model) {
        if (!(employeeRepository.existsById(model.getEmployeeId()))) {
            return false;
        }
        return model.getRoomId() == null || roomRepository.existsById(model.getRoomId());
    }
}
