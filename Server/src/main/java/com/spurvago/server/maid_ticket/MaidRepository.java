package com.spurvago.server.maid_ticket;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaidRepository extends PagingAndSortingRepository<MaidTicket, Long> {
    MaidTicket findById(long id);
    Page<MaidTicket> findAll(Pageable pageable);
}
