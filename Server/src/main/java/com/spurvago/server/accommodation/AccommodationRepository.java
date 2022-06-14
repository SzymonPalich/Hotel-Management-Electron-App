package com.spurvago.server.accommodation;

import com.spurvago.database.Accommodation;
import com.spurvago.database.Client;
import com.spurvago.database.Room;
import com.spurvago.database.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;

import static com.spurvago.components.Utils.asLikeQuery;

@Repository
public interface AccommodationRepository
        extends PagingAndSortingRepository<Accommodation, Long>, JpaSpecificationExecutor<Accommodation> {

    /**
     * Wyszukiwanie z rezerwacjach
     * @param searchWords
     * @return
     */
    static Specification<Accommodation> search(List<String> searchWords) {
        return (r, q, b) -> {
            Join<Accommodation, Client> joinClient = r.join("client");
            Join<Accommodation, Room> joinRoom = r.join("room");
            Join<Room, RoomType> joinRoomType = joinRoom.join("roomType");
            Predicate predicate = null;
            Predicate tempPredicate;

            //<editor-fold desc="Predicate Builder">
            for (String searchWord : searchWords) {
                tempPredicate =
                        b.or(
                                b.like(joinClient.get("firstName"), asLikeQuery(searchWord)),
                                b.like(joinClient.get("lastName"), asLikeQuery(searchWord)),
                                b.like(joinRoom.get("roomNumber").as(String.class), asLikeQuery(searchWord)),
                                b.like(joinRoomType.get("type"), asLikeQuery(searchWord)),
                                b.like(joinRoom.get("status").as(String.class), asLikeQuery(searchWord)),
                                b.like(r.get("startDate").as(String.class), asLikeQuery(searchWord)),
                                b.like(r.get("endDate").as(String.class), asLikeQuery(searchWord))
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

    Optional<Accommodation> findById(Long id);

    List<Accommodation> findAllByRoom(Room room);

    Page<Accommodation> findAll(Pageable pageable);
}
