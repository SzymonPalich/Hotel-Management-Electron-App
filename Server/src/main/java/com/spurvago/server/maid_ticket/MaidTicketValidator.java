package com.spurvago.server.maid_ticket;

import com.spurvago.components.Validator;
import com.spurvago.database.MaidTicket;
import com.spurvago.server.employee.EmployeeRepository;
import com.spurvago.server.maid_ticket.models.MaidTicketFM;
import com.spurvago.server.maid_ticket.models.RefillFM;
import com.spurvago.server.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * Walidator zgłoszeń sprzątania
 */
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
        if (!(model.getFinalizationDate() == null)) {
            return false;
        }
        if (!(model.getEmployeeId() == null) && !(employeeRepository.existsById(model.getEmployeeId()))) {
            return false;
        }
        return model.getRoomId() == null || roomRepository.existsById(model.getRoomId());
    }

    public boolean validate(MaidTicket model) {
        return model.getFinalizationDate() == null;
    }

    /**
     * Walidacja czy produkty wprowadzone podczas finalizacji sprzątania istnieją w magazynie
     * @param availableProductIds
     * @param refillEntity
     * @return
     */
    public boolean validate(HashSet<Long> availableProductIds, RefillFM refillEntity) {
        for (var x : refillEntity.getProducts().keySet()) {
            if (!(availableProductIds.contains(x))) {
                return false;
            }
        }
        return true;
    }
}
