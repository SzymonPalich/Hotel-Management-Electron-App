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
import javax.persistence.criteria.Predicate;
import java.util.List;
import static com.spurvago.components.Utils.asLikeQuery;

@Repository
public interface MaidTicketRepository extends PagingAndSortingRepository<MaidTicket, Long>, JpaSpecificationExecutor<MaidTicket> {
    MaidTicket findById(long id);
    Page<MaidTicket> findAll(Pageable pageable);

    static Specification<MaidTicket> search(List<String> searchWords) {
        return (r, q, b) -> {
            Join<MaidTicket, Employee> join = r.join("employee");
            Predicate predicate = null;
            Predicate tempPredicate;

            for (String searchWord : searchWords) {
                tempPredicate =
                        b.or(
                                b.like(join.get("firstName"), asLikeQuery(searchWord)),
                                b.like(join.get("lastName"), asLikeQuery(searchWord)),
                                b.like(r.get("roomId").as(String.class), asLikeQuery(searchWord)),
                                b.like(r.get("finalizationDate").as(String.class), asLikeQuery(searchWord))
                        );
                if (searchWord.equals(searchWords.get(0)))
                    predicate = tempPredicate;
                else {
                    predicate = b.and(predicate, tempPredicate);
                }
            }
            return predicate;
        };
    }
}
