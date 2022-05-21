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
        dest.setEmployee(employeeRepository.findById(src.getEmployeeId()));
        dest.setName(src.getName());
        dest.setDescription(src.getDescription());
        dest.setPartsPrice(src.getPartsPrice());
        dest.setTechnicianReport(src.getTechnicianReport());
        dest.setFinalizationDate(src.getFinalizationDate());

        return dest;
    }

    void mapToEntity(MaintenanceTicket dest, MaintenanceTicketFM src) {
        dest.setRoom(roomRepository.findById(src.getRoomId()));
        dest.setEmployee(employeeRepository.findById(src.getEmployeeId()));
        dest.setName(src.getName());
        dest.setDescription(src.getDescription());
        dest.setPartsPrice(src.getPartsPrice());
        dest.setTechnicianReport(src.getTechnicianReport());
        dest.setFinalizationDate(src.getFinalizationDate());

    }

    MaintenanceTicketVM mapToVM(MaintenanceTicket src) {
        MaintenanceTicketVM dest = new MaintenanceTicketVM();

        dest.setId(src.getId());
        dest.setRoomNumber(src.getRoom().getRoomNumber());
        dest.setRoomType(src.getRoom().getRoomType().getType());
        dest.setRoomStatus(src.getRoom().getStatus());
        dest.setEmployeeLastName(src.getEmployee().getFirstName());
        dest.setEmployeeLastName(src.getEmployee().getLastName());
        dest.setName(src.getName());
        dest.setDescription(src.getDescription());
        dest.setPartsPrice(src.getPartsPrice());
        dest.setTechnicianReport(src.getTechnicianReport());
        dest.setFinalizationDate(src.getFinalizationDate());

        return dest;
    }
}
