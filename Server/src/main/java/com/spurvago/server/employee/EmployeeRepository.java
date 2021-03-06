package com.spurvago.server.employee;


import com.spurvago.database.Employee;
import com.spurvago.server.employee.models.EmployeeSelect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;

import static com.spurvago.components.Utils.asLikeQuery;

/**
 * Role pracowników
 */
@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    /**
     * Wyszukiwanie pracowników po wprowadzonych wartościach
     * @param searchWords
     * @return
     */
    static Specification<Employee> search(List<String> searchWords) {
        return (r, q, b) -> {
            Predicate predicate = null;
            Predicate tempPredicate;

            //<editor-fold desc="Predicate Builder">
            for (String searchWord : searchWords) {
                tempPredicate =
                        b.or(
                                b.like(r.get("firstName"), asLikeQuery(searchWord)),
                                b.like(r.get("lastName"), asLikeQuery(searchWord)),
                                b.like(r.get("email"), asLikeQuery(searchWord)),
                                b.like(r.get("phoneNumber"), asLikeQuery(searchWord)),
                                b.like(r.get("pesel"), asLikeQuery(searchWord)),
                                b.like(r.get("employmentDate").as(String.class), asLikeQuery(searchWord)),
                                b.like(r.get("dismissalDate").as(String.class), asLikeQuery(searchWord)),
                                b.like(r.get("salary").as(String.class), asLikeQuery(searchWord))
                        );
                if (searchWord.equals(searchWords.get(0))) {
                    predicate = tempPredicate;
                } else {
                    predicate = b.and(predicate, tempPredicate);
                }
            }
            //</editor-fold>

            return predicate;
        };
    }

    Optional<Employee> findById(Long id);

    List<Employee> findEmployeesByPosition(String position);

    Page<Employee> findAll(Pageable pageable);

    Employee findByEmail(String email);

    List<Employee> findAll();
}
