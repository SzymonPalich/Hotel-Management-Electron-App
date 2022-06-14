package com.spurvago.server.refill;
import com.spurvago.database.Refill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefillRepository extends CrudRepository<Refill, Long> {
}