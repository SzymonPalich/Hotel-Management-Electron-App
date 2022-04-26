package com.spurvago.server.client;

import com.spurvago.components.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long>, JpaSpecificationExecutor<Client> {

    Client findById(long id);

    Page<Client> findAll(Pageable pageable);

    static Specification<Client> search(List<String> searchWords) {
        return Utils.filter(searchWords);
    }
}
