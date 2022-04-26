package com.spurvago.server.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Expression;
import java.util.List;


@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long>, JpaSpecificationExecutor<Client> {

    Client findById(long id);

    Page<Client> findAll(Pageable pageable);

    static Specification<Client> search(List<String> searchWords) {
        if (searchWords.size() == 1) {
            return (r, q, b) -> {
                Expression<String> firstNameLower = b.lower(r.get("firstName"));
                Expression<String> lastNameLower = b.lower(r.get("lastName"));
                Expression<String> emailLower = b.lower(r.get("email"));
                Expression<String> phoneNumberLower = b.lower(r.get("phoneNumber"));
                return b.and(
                        b.or(
                                b.like(firstNameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(lastNameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(emailLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(phoneNumberLower, "%" + searchWords.get(0).toLowerCase() + "%")
                        )
                );
            };
        } else if (searchWords.size() == 2) {
             return (r, q, b) -> {
                 Expression<String> firstNameLower = b.lower(r.get("firstName"));
                 Expression<String> lastNameLower = b.lower(r.get("lastName"));
                 Expression<String> emailLower = b.lower(r.get("email"));
                 Expression<String> phoneNumberLower = b.lower(r.get("phoneNumber"));
                 return b.and(
                          b.or(
                                  b.like(firstNameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                  b.like(lastNameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                  b.like(emailLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                  b.like(phoneNumberLower, "%" + searchWords.get(0).toLowerCase() + "%")
                          ),
                          b.or(
                                  b.like(lastNameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                  b.like(emailLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                  b.like(phoneNumberLower, "%" + searchWords.get(1).toLowerCase() + "%")
                          )
                 );
             };
         } else if(searchWords.size() == 3) {
             return (r, q, b) -> {
                 Expression<String> firstNameLower = b.lower(r.get("firstName"));
                 Expression<String> lastNameLower = b.lower(r.get("lastName"));
                 Expression<String> emailLower = b.lower(r.get("email"));
                 Expression<String> phoneNumberLower = b.lower(r.get("phoneNumber"));
                 return b.and(
                         b.or(
                                 b.like(firstNameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                 b.like(lastNameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                 b.like(emailLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                 b.like(phoneNumberLower, "%" + searchWords.get(0).toLowerCase() + "%")
                         ),
                         b.or(
                                 b.like(firstNameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                 b.like(lastNameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                 b.like(emailLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                 b.like(phoneNumberLower, "%" + searchWords.get(1).toLowerCase() + "%")
                         ),
                         b.or(
                                 b.like(firstNameLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                 b.like(lastNameLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                 b.like(emailLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                 b.like(phoneNumberLower, "%" + searchWords.get(2).toLowerCase() + "%")
                         )
                 );
             };
         } else {
            return (r, q, b) -> {
                Expression<String> firstNameLower = b.lower(r.get("firstName"));
                Expression<String> lastNameLower = b.lower(r.get("lastName"));
                Expression<String> emailLower = b.lower(r.get("email"));
                Expression<String> phoneNumberLower = b.lower(r.get("phoneNumber"));
                return b.and(
                        b.or(
                                b.like(firstNameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(lastNameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(emailLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(phoneNumberLower, "%" + searchWords.get(0).toLowerCase() + "%")
                        ),
                        b.or(
                                b.like(firstNameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(lastNameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(emailLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(phoneNumberLower, "%" + searchWords.get(1).toLowerCase() + "%")
                        ),
                        b.or(
                                b.like(firstNameLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                b.like(lastNameLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                b.like(emailLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                b.like(phoneNumberLower, "%" + searchWords.get(2).toLowerCase() + "%")
                        ),
                        b.or(
                                b.like(firstNameLower, "%" + searchWords.get(3).toLowerCase() + "%"),
                                b.like(lastNameLower, "%" + searchWords.get(3).toLowerCase() + "%"),
                                b.like(emailLower, "%" + searchWords.get(3).toLowerCase() + "%"),
                                b.like(phoneNumberLower, "%" + searchWords.get(3).toLowerCase() + "%")
                        )
                );
            };
        }
    }
}
