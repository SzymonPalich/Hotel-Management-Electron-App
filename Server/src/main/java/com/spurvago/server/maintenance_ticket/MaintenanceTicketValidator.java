package com.spurvago.server.maintenance_ticket;

import com.spurvago.components.Utils;
import com.spurvago.components.Validator;
import com.spurvago.server.employee.EmployeeRepository;
import com.spurvago.server.maintenance_ticket.models.MaintenanceTicketFM;
import com.spurvago.server.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Walidator zgłoszeń naprawy
 */
@Component
public class MaintenanceTicketValidator extends Validator {
    private final MaintenanceTicketRepository maintenanceTicketRepository;
    private final RoomRepository roomRepository;
    private final EmployeeRepository employeeRepository;

    /**
     *
     * @param maintenanceTicketRepository
     * @param roomRepository
     * @param employeeRepository
     */
    @Autowired
    public MaintenanceTicketValidator(MaintenanceTicketRepository maintenanceTicketRepository, RoomRepository roomRepository, EmployeeRepository employeeRepository) {
        this.maintenanceTicketRepository = maintenanceTicketRepository;
        this.roomRepository = roomRepository;
        this.employeeRepository = employeeRepository;
    }

    /**
     *
     * @param model
     * @return
     */
    public boolean validate(MaintenanceTicketFM model) {
        if (!(roomRepository.existsById(model.getRoomId()))) {
            return false;
        }
        if (model.getEmployeeId() != null) {
            if (!(employeeRepository.existsById(model.getEmployeeId()))) {
                return false;
            }
        }
        if (Utils.isNullOrBlank(model.getName())) {
            return false;
        }
        if (!(haveLength(model.getName(), 50))) {
            return false;
        }
        if(Utils.isNullOrBlank(model.getDescription())){
            return false;
        }
        if (!(haveLength(model.getDescription(), 50))) {
            return false;
        }

        if (model.getPartsPrice() != null
                && !(correctDecimal(model.getPartsPrice(), 8, 2))) {
            return false;
        }

        if (model.getTechnicianReport() != null) {
            if (isEmpty(model.getTechnicianReport())) {
                return false;
            }
            if (!(haveLength(model.getTechnicianReport(), 300))) {
                return false;
            }
        }
        return true;
    }
}
