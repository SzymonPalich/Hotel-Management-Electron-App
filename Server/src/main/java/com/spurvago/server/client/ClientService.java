package com.spurvago.server.client;

import com.spurvago.components.IBaseService;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import com.spurvago.database.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Objects;

import static com.spurvago.components.Utils.asLikeQuery;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public record ClientService(ClientRepository clientRepository) implements IBaseService<Client> {

    //<editor-fold desc="find()">
    @Override
    public Client find(long id) {
        return clientRepository.findById(id);
    }
    //</editor-fold>

    //<editor-fold desc="getList()">
    @Override
    public ListPaginated<Client> getList(Pager pager, String search) {
        Pageable pageable = pager.makePageable();
        Page<Client> entities = clientRepository.findAll(makeSpecification(search), pageable);

        return new ListPaginated<>(entities, pager);
    }
    //</editor-fold>

    //<editor-fold desc="create()">
    @Override
    public Client create(Client newEntity) {
        if (!newEntity.validate())
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        if (!checkConstraints(newEntity.getPhoneNumber(), newEntity.getEmail()))
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        return clientRepository.save(newEntity);
    }
    //</editor-fold>

    //<editor-fold desc="update()">
    @Override
    public Client update(Client oldEntity, Client newEntity) {
        if (!newEntity.validate())
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);

        if (!Objects.equals(oldEntity.getPhoneNumber(), newEntity.getPhoneNumber())
                && newEntity.getPhoneNumber() != null
                && clientRepository.existsByPhoneNumber(newEntity.getPhoneNumber()))
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);

        if (!Objects.equals(oldEntity.getEmail(), newEntity.getEmail())
                && newEntity.getEmail() != null
                && clientRepository.existsByEmail(newEntity.getEmail()))
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);

        oldEntity.map(newEntity);

        return clientRepository.save(oldEntity);
    }
    //</editor-fold>

    //<editor-fold desc="delete()">
    @Override
    public void delete(Client Entity) {
        clientRepository.delete(Entity);
    }
    //</editor-fold>

    //<editor-fold desc="makeSpecification()">
    private Specification<Client> makeSpecification(String search) {
        Specification<Client> specification;
        if (!Utils.isNullOrBlank(search)) {
            List<String> words = List.of(search.split("\\s"));
            if (words.size() > 4)
                throw new ResponseStatusException(UNPROCESSABLE_ENTITY);

            specification = filter(words);
        } else {
            specification = null;
        }
        return specification;
    }
    //</editor-fold>

    //<editor-fold desc="filter()">
    Specification<Client> filter(List<String> searchWords) {
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
    //</editor-fold>

    //<editor-fold desc="checkConstraints()">
    private boolean checkConstraints(String phoneNumber, String email){
        if(clientRepository.existsByPhoneNumber(phoneNumber))
            return false;
        return !clientRepository.existsByEmail(email);
    }
    //</editor-fold>
}
