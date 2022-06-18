package com.spurvago.server.room_type;

import com.spurvago.database.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;

import static com.spurvago.components.Utils.asLikeQuery;

/**
 * Repozytorium typów pokojów
 */
@Repository
public interface RoomTypeRepository extends PagingAndSortingRepository<RoomType, Long>, JpaSpecificationExecutor<RoomType> {

    static Specification<RoomType> search(List<String> searchWords) {
        return (r, q, b) -> {
            Predicate predicate = null;
            Predicate tempPredicate;

            //<editor-fold desc="Predicate Builder">
            for (String searchWord : searchWords) {
                tempPredicate =
                        b.or(
                                b.like(r.get("type"), asLikeQuery(searchWord)),
                                b.like(r.get("price").as(String.class), asLikeQuery(searchWord))
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

    Optional<RoomType> findById(Long id);

    Page<RoomType> findAll(Pageable pageable);
}
