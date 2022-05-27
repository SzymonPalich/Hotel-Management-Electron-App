package com.spurvago.server.product;

import com.spurvago.database.Product;
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

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    static Specification<Product> search(List<String> searchWords) {
        return (r, q, b) -> {
            Predicate predicate = null;
            Predicate tempPredicate;

            //<editor-fold desc="Predicate Builder">
            for (String searchWord : searchWords) {
                tempPredicate =
                        b.or(
                                b.like(r.get("productName"), asLikeQuery(searchWord)),
                                b.like(r.get("retailPrice").as(String.class), asLikeQuery(searchWord)),
                                b.like(r.get("wholesalePrice").as(String.class), asLikeQuery(searchWord))
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

    Optional<Product> findById(Long id);

    Page<Product> findAll(Pageable pageable);
}