package com.spurvago.server.room_type;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends PagingAndSortingRepository<RoomType, Long> {

    RoomType findById(long id);

    Page<RoomType> findAll(Pageable pageable);
}
