package com.spurvago.server.maid_ticket;

import com.spurvago.database.Employee;
import com.spurvago.database.MaidTicket;
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
public interface MaidTicketRepository extends PagingAndSortingRepository<MaidTicket, Long>, JpaSpecificationExecutor<MaidTicket> {
    static Specification<MaidTicket> search(List<String> searchWords) {
        return (r, q, b) -> {
            Join<MaidTicket, Employee> joinEmp = r.join("employee");
            Join<MaidTicket, Room> joinRoom = r.join("room");
            Join<Room, RoomType> joinRoomType = joinRoom.join("roomType");
            Predicate predicate = null;
            Predicate tempPredicate;

            //<editor-fold desc="Predicate Builder">
            for (String searchWord : searchWords) {
                tempPredicate =
                        b.or(
                                b.like(joinEmp.get("firstName"), asLikeQuery(searchWord)),
                                b.like(joinEmp.get("lastName"), asLikeQuery(searchWord)),
                                b.like(joinRoom.get("roomNumber").as(String.class), asLikeQuery(searchWord)),
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

    Optional<MaidTicket> findById(Long id);

    Page<MaidTicket> findAll(Pageable pageable);
}
