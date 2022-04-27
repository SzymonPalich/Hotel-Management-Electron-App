package com.spurvago.server.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    Client findById(long id);
    Client findByPhoneNumber(String phoneNumber);

    Page<Client> findAll(Pageable pageable);
}
