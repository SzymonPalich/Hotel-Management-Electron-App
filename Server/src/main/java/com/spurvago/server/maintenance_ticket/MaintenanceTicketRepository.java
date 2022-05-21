package com.spurvago.server.maintenance_ticket;

import com.spurvago.database.Employee;
import com.spurvago.database.MaintenanceTicket;
import com.spurvago.database.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.List;

import static com.spurvago.components.Utils.asLikeQuery;

@Repository
public interface MaintenanceTicketRepository extends PagingAndSortingRepository<MaintenanceTicket, Long>, JpaSpecificationExecutor<MaintenanceTicket> {
    static Specification<MaintenanceTicket> search(List<String> searchWords) {
        return (r, q, b) -> {
            Join<MaintenanceTicket, Employee> joinEmp = r.join("technician");
            Join<MaintenanceTicket, Room> joinRoom = r.join("room");
            Predicate predicate = null;
            Predicate tempPredicate;

            //<editor-fold desc="Predicate Builder">
            for (String searchWord : searchWords) {
                tempPredicate =
                        b.or(
                                b.like(joinRoom.get("roomNumber").as(String.class), asLikeQuery(searchWord)),
                                b.like(joinRoom.get("roomType"), asLikeQuery(searchWord)),
                                b.like(joinRoom.get("status").as(String.class), asLikeQuery(searchWord)),
                                b.like(joinEmp.get("firstName"), asLikeQuery(searchWord)),
                                b.like(joinEmp.get("lastName"), asLikeQuery(searchWord)),
                                b.like(r.get("name"), asLikeQuery(searchWord)),
                                b.like(r.get("description"), asLikeQuery(searchWord)),
                                b.like(r.get("partsPrice").as(String.class), asLikeQuery(searchWord)),
                                b.like(r.get("technicianReport"), asLikeQuery(searchWord)),
                                b.like(r.get("finalizationDate").as(String.class), asLikeQuery(searchWord))
                        );
                if (searchWord.equals(searchWords.get(0)))
                    predicate = tempPredicate;
                else {
                    predicate = b.and(predicate, tempPredicate);
                }
            }
            //</editor-fold>

            return predicate;
        };
    }

    MaintenanceTicket findById(long id);

    Page<MaintenanceTicket> findAll(Pageable pageable);
}
