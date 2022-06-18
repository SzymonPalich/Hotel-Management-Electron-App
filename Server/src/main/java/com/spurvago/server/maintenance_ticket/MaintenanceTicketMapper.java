package com.spurvago.server.maintenance_ticket;

import com.spurvago.database.MaintenanceTicket;
import com.spurvago.server.employee.EmployeeRepository;
import com.spurvago.server.maintenance_ticket.models.MaintenanceTicketFM;
import com.spurvago.server.maintenance_ticket.models.MaintenanceTicketVM;
import com.spurvago.server.room.RoomRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper zgłoszeń naprawy
 * @param employeeRepository
 * @param roomRepository
 */
@Component
public record MaintenanceTicketMapper(EmployeeRepository employeeRepository, RoomRepository roomRepository) {

    MaintenanceTicket mapToEntity(MaintenanceTicketFM src) {
        MaintenanceTicket dest = new MaintenanceTicket();
        if (src.getEmployeeId() != null) {
            dest.setEmployee(employeeRepository.findById(src.getEmployeeId()).orElse(null));
        }
        dest.setRoom(roomRepository.findById(src.getRoomId()).orElse(null));
        dest.setName(src.getName());
        dest.setDescription(src.getDescription());
        dest.setPartsPrice(src.getPartsPrice());
        dest.setTechnicianReport(src.getTechnicianReport());
        dest.setFinalizationDate(src.getFinalizationDate());

        return dest;
    }

    void mapToEntity(MaintenanceTicket dest, MaintenanceTicketFM src) {
        if (src.getEmployeeId() != null) {
            dest.setEmployee(employeeRepository.findById(src.getEmployeeId()).orElse(null));
        }
        dest.setRoom(roomRepository.findById(src.getRoomId()).orElse(null));
        dest.setName(src.getName());
        dest.setDescription(src.getDescription());
        dest.setPartsPrice(src.getPartsPrice());
        dest.setTechnicianReport(src.getTechnicianReport());
        dest.setFinalizationDate(src.getFinalizationDate());

    }

    MaintenanceTicketVM mapToVM(MaintenanceTicket src) {
        MaintenanceTicketVM dest = new MaintenanceTicketVM();

        dest.setId(src.getId());

        if(src.getEmployee() != null) {
            dest.setEmployeeId(src.getEmployee().getId());
        }
        dest.setRoomId(src.getRoom().getId());
        dest.setRoomNumber(src.getRoom().getRoomNumber());
        dest.setRoomType(src.getRoom().getRoomType().getType());
        dest.setRoomStatus(src.getRoom().getStatus());
        dest.setName(src.getName());
        dest.setDescription(src.getDescription());
        dest.setPartsPrice(src.getPartsPrice());
        dest.setTechnicianReport(src.getTechnicianReport());
        dest.setFinalizationDate(src.getFinalizationDate());
        if (src.getEmployee() != null) {
            dest.setEmployeeLastName(src.getEmployee().getFirstName());
            dest.setEmployeeLastName(src.getEmployee().getLastName());
        }

        return dest;
    }

    List<MaintenanceTicketVM> mapToList(List<MaintenanceTicket> srcList) {
        List<MaintenanceTicketVM> destList = new ArrayList<>();
        for (MaintenanceTicket srcEntity : srcList) {
            destList.add(mapToVM(srcEntity));
        }

        return destList;
    }
}
