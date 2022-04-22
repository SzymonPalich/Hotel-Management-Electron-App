package com.spurvago.server.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Expression;


@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long>, JpaSpecificationExecutor<Client> {

    Client findById(long id);

    Page<Client> findAll(Pageable pageable);

    default Specification<Client> search(String searchWord) {
        return (root, query, builder) -> {
            Expression<String> surnameLower = builder.lower(root.get("lastName"));
            return builder.like(surnameLower, "%" + searchWord.toLowerCase() + "%");
        };
    }
}
