package com.spurvago.server.maintenance_ticket;

import com.spurvago.database.MaintenanceTicket;
import com.spurvago.server.employee.EmployeeRepository;
import com.spurvago.server.maintenance_ticket.models.MaintenanceTicketFM;
import com.spurvago.server.maintenance_ticket.models.MaintenanceTicketVM;
import com.spurvago.server.room.RoomRepository;
import org.springframework.stereotype.Component;

@Component
public record MaintenanceTicketMapper(EmployeeRepository employeeRepository, RoomRepository roomRepository) {
    MaintenanceTicket mapToEntity(MaintenanceTicketFM src) {
        MaintenanceTicket dest = new MaintenanceTicket();
        dest.setRoom(roomRepository.findById(src.getRoomId()));
        dest.setTechnician(employeeRepository.findById(src.getEmployeeId()));
        dest.setName(src.getName());
        dest.setDescription(src.getDescription());
        dest.setPartsPrice(src.getPartsPrice());
        dest.setTechnicianReport(src.getTechnicianReport());
        dest.setFinalizationDate(src.getFinalizationDate());

        return dest;
    }

    MaintenanceTicket mapToEntity(MaintenanceTicket dest, MaintenanceTicketFM src) {
        dest.setRoom(roomRepository.findById(src.getRoomId()));
        dest.setTechnician(employeeRepository.findById(src.getEmployeeId()));
        dest.setName(src.getName());
        dest.setDescription(src.getDescription());
        dest.setPartsPrice(src.getPartsPrice());
        dest.setTechnicianReport(src.getTechnicianReport());
        dest.setFinalizationDate(src.getFinalizationDate());

        return dest;
    }

    MaintenanceTicketVM mapToVM(MaintenanceTicket src) {
        MaintenanceTicketVM dest = new MaintenanceTicketVM();

        dest.setId(src.getId());
        dest.setRoomNumber(src.getRoom().getRoomNumber());
        dest.setRoomType(src.getRoom().getRoomType().getType());
        dest.setRoomStatus(src.getRoom().getStatus());
        if (src.getTechnician() == null) {
            dest.setEmployeeFirstName("");
            dest.setEmployeeLastName("");
        } else {
            dest.setEmployeeLastName(src.getTechnician().getFirstName());
            dest.setEmployeeLastName(src.getTechnician().getLastName());
        }
        dest.setName(src.getName());
        dest.setDescription(src.getDescription());
        dest.setPartsPrice(src.getPartsPrice());
        dest.setTechnicianReport(src.getTechnicianReport());
        dest.setFinalizationDate(src.getFinalizationDate());

        return dest;
    }
}
