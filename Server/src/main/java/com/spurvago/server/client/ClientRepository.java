package com.spurvago.server.client;

import com.spurvago.database.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.List;

import static com.spurvago.components.Utils.asLikeQuery;


@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long>, JpaSpecificationExecutor<Client> {
    Client findById(long id);

    Boolean existsByPhoneNumber(String phoneNumber);

    Boolean existsByEmail(String email);

    Page<Client> findAll(Pageable pageable);

    static Specification<Client> search(List<String> searchWords) {
        return (r, q, b) -> {
            Predicate predicate = null;
            Predicate tempPredicate;

            for (String searchWord : searchWords) {
                tempPredicate =
                        b.or(
                                b.like(r.get("firstName"), asLikeQuery(searchWord)),
                                b.like(r.get("lastName"), asLikeQuery(searchWord)),
                                b.like(r.get("email"), asLikeQuery(searchWord)),
                                b.like(r.get("phoneNumber"), asLikeQuery(searchWord))
                        );
                if (searchWord.equals(searchWords.get(0)))
                    predicate = tempPredicate;
                else
                    predicate = b.and(predicate, tempPredicate);
            }

            return predicate;
        };
    }
}
