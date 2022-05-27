package com.spurvago.server.room;

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
public interface RoomRepository extends PagingAndSortingRepository<Room, Long>, JpaSpecificationExecutor<Room> {
    static Specification<Room> search(List<String> searchWords) {
        return (r, q, b) -> {
            Join<Room, RoomType> join = r.join("roomType");
            Predicate predicate = null;
            Predicate tempPredicate;

            //<editor-fold desc="Predicate Builder">
            for (String searchWord : searchWords) {
                tempPredicate =
                        b.or(
                                b.like(r.get("roomNumber").as(String.class), asLikeQuery(searchWord)),
                                b.like(join.get("Type"), asLikeQuery(searchWord)),
                                b.like(r.get("status").as(String.class), asLikeQuery(searchWord))
                        );
                if (searchWord.equals(searchWords.get(0))) {
                    predicate = tempPredicate;
                } else {
                    predicate = b.and(predicate, tempPredicate);
                }
            }
            //</editor-fold>

            return predicate;
        };
    }

    Optional<Room> findById(Long id);

    Page<Room> findAll(Pageable pageable);
}
