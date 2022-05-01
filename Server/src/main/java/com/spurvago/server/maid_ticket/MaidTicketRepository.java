package com.spurvago.server.maid_ticket;

import com.spurvago.database.Employee;
import com.spurvago.database.MaidTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import java.util.List;

@Repository
public interface MaidTicketRepository extends PagingAndSortingRepository<MaidTicket, Long>, JpaSpecificationExecutor<MaidTicket> {
    MaidTicket findById(long id);
    Page<MaidTicket> findAll(Pageable pageable);

    static Specification<MaidTicket> search(List<String> searchWords) {
        return (r, q, b) -> {
            Join<MaidTicket, Employee> join = r.join("employee");
            Expression<String> name = join.get("name");
            return b.or(b.like(name, "%" + searchWords.get(0).toLowerCase() + "%"));
        };
//        if (searchWords.size() == 1) {
//            return (r, q, b) -> {
//                Expression<String> maid_id = r.get("maid_id");
//                Expression<String> room_id = r.get("room_id");
//                return b.and(
//                        b.or(
//                                b.like(maid_id.as(String.class), "%" + searchWords.get(0).toLowerCase() + "%"),
//                                b.like(room_id.as(String.class), "%" + searchWords.get(0).toLowerCase() + "%")
//                        ).or(b.like(name, "%" + searchWords.get(0).toLowerCase() + "%"));
//                );
//            };
//        } else {
//            return (r, q, b) -> {
//                Expression<String> maid_id = r.get("maid_id");
//                Expression<String> room_id = r.get("room_id");
//                return b.and(
//                        b.or(
//                                b.like(maid_id.as(String.class), "%" + searchWords.get(0).toLowerCase() + "%"),
//                                b.like(room_id.as(String.class), "%" + searchWords.get(0).toLowerCase() + "%")
//                        ),
//                        b.or(
//                                b.like(maid_id.as(String.class), "%" + searchWords.get(1).toLowerCase() + "%"),
//                                b.like(room_id.as(String.class), "%" + searchWords.get(1).toLowerCase() + "%")
//                        )
//                );
//            };
//        }
    }
}
