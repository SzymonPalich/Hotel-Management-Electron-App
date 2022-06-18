package com.spurvago.server.refill;
import com.spurvago.database.MaidTicket;
import com.spurvago.database.Refill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repozytorium do uzupełniania minibaru. Wykorzystywane są w nim metody wewnętrzne
 */
@Repository
public interface RefillRepository extends CrudRepository<Refill, Long> {

}
