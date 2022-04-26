package com.spurvago.server.room_type;

import com.spurvago.server.client.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Expression;
import java.util.List;

@Repository
public interface RoomTypeRepository extends PagingAndSortingRepository<RoomType, Long>, JpaSpecificationExecutor<RoomType> {

    RoomType findById(long id);

    Page<RoomType> findAll(Pageable pageable);

    static Specification<RoomType> search(List<String> searchWords) {
        if (searchWords.size() == 1) {
            return (r, q, b) -> {
                Expression<String> type = b.lower(r.get("Type"));
                Expression<String> price = r.get("Price");
                return b.and(
                        b.or(
                                b.like(type, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(price.as(String.class), "%" + searchWords.get(0) + "%")
                        )
                );
            };
        } else {
            return (r, q, b) -> {
                Expression<String> type = b.lower(r.get("Type"));
                Expression<String> price = r.get("Price");
                return b.and(
                        b.or(
                                b.like(type, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(price.as(String.class), "%" + searchWords.get(0).toLowerCase() + "%")
                        ),
                        b.or(
                                b.like(type, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(price.as(String.class), "%" + searchWords.get(1).toLowerCase() + "%")
                        )
                );
            };
        }
    }
}
