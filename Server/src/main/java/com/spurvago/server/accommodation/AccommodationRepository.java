package com.spurvago.server.accommodation;

import com.spurvago.database.Accommodation;
import com.spurvago.database.Client;
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
public interface AccommodationRepository extends PagingAndSortingRepository<Accommodation, Long>, JpaSpecificationExecutor<Accommodation> {
    static Specification<Accommodation> search(List<String> searchWords) {
        return (r, q, b) -> {
            Join<Accommodation, Client> joinClient = r.join("client");
            Join<Accommodation, Room> joinRoom = r.join("room");
            Predicate predicate = null;
            Predicate tempPredicate;

            for (String searchWord : searchWords) {
                tempPredicate =
                        b.or(
                                b.like(joinClient.get("firstName"), asLikeQuery(searchWord)),
                                b.like(joinClient.get("lastName"), asLikeQuery(searchWord)),
                                b.like(joinRoom.get("roomNumber").as(String.class), asLikeQuery(searchWord)),
                                b.like(joinRoom.get("roomType"), asLikeQuery(searchWord)),
                                b.like(joinRoom.get("roomStatus").as(String.class), asLikeQuery(searchWord)),
                                b.like(r.get("startDate").as(String.class), asLikeQuery(searchWord)),
                                b.like(r.get("endDate").as(String.class), asLikeQuery(searchWord))
                                // bool
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

    Accommodation findById(long id);

    Page<Accommodation> findAll(Pageable pageable);
}