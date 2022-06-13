package com.spurvago.server.maintenance_ticket;

import com.spurvago.database.Employee;
import com.spurvago.database.MaintenanceTicket;
import com.spurvago.database.Room;
import com.spurvago.database.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;

import static com.spurvago.components.Utils.asLikeQuery;

@Repository
public interface MaintenanceTicketRepository extends PagingAndSortingRepository<MaintenanceTicket, Long>, JpaSpecificationExecutor<MaintenanceTicket> {
    static Specification<MaintenanceTicket> search(List<String> searchWords) {
        return (r, q, b) -> {
            Join<MaintenanceTicket, Room> joinRoom = r.join("room");
            Predicate predicate = null;
            Predicate tempPredicate;

            //<editor-fold desc="Predicate Builder">
            for (String searchWord : searchWords) {
                tempPredicate =
                        b.or(
                                b.like(joinRoom.get("roomNumber").as(String.class), asLikeQuery(searchWord)),
                                b.like(r.get("name"), asLikeQuery(searchWord)),
                                b.like(r.get("description"), asLikeQuery(searchWord))
                        );
                if (searchWord.equals(searchWords.get(0))) {
                    predicate = tempPredicate;
                    System.out.println(searchWord);
                }
                else {
                    predicate = b.and(predicate, tempPredicate);
                }
            }
            //</editor-fold>

            return predicate;
        };
    }

    Optional<MaintenanceTicket> findById(Long id);

    Page<MaintenanceTicket> findAll(Pageable pageable);
}
