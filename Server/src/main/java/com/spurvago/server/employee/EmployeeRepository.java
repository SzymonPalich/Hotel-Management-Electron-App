package com.spurvago.server.employee;

import com.spurvago.database.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.Expression;
import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Employee findById(long id);

    Page<Employee> findAll(Pageable pageable);

    static Specification<Employee> search(List<String> searchWords) {
        if (searchWords.size() == 1) {
            return (r, q, b) -> {
                Expression<String> nameLower = b.lower(r.get("name"));
                Expression<String> surnameLower = b.lower(r.get("surname"));
                Expression<String> positionLower = r.get("position");
                Expression<String> salary = r.get("salary");
                Expression<String> emailLower = b.lower(r.get("email"));
                Expression<String> phoneNumber = b.lower(r.get("phoneNumber"));
                Expression<String> pesel = b.lower(r.get("pesel"));
                Expression<String> employmentDate = r.get("employmentDate");
                Expression<String> dismissalDate = r.get("dismissalDate");
                return b.and(
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(0) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(emailLower, "%" + searchWords.get(0) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(0) + "%"),
                                b.like(pesel, "%" + searchWords.get(0) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(0) + "%")
                        )
                );
            };
        } else if (searchWords.size() == 2) {
            return (r, q, b) -> {
                Expression<String> nameLower = b.lower(r.get("name"));
                Expression<String> surnameLower = b.lower(r.get("surname"));
                Expression<String> positionLower = r.get("position");
                Expression<String> salary = r.get("salary");
                Expression<String> emailLower = b.lower(r.get("email"));
                Expression<String> phoneNumber = b.lower(r.get("phoneNumber"));
                Expression<String> pesel = b.lower(r.get("pesel"));
                Expression<String> employmentDate = r.get("employmentDate");
                Expression<String> dismissalDate = r.get("dismissalDate");
                return b.and(
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(0) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(emailLower, "%" + searchWords.get(0) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(0) + "%"),
                                b.like(pesel, "%" + searchWords.get(0) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(0) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(1) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(emailLower, "%" + searchWords.get(1) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(1) + "%"),
                                b.like(pesel, "%" + searchWords.get(1) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(1) + "%")
                        )
                );
            };
        } else if (searchWords.size() == 3) {
            return (r, q, b) -> {
                Expression<String> nameLower = b.lower(r.get("name"));
                Expression<String> surnameLower = b.lower(r.get("surname"));
                Expression<String> positionLower = r.get("position");
                Expression<String> salary = r.get("salary");
                Expression<String> emailLower = b.lower(r.get("email"));
                Expression<String> phoneNumber = b.lower(r.get("phoneNumber"));
                Expression<String> pesel = b.lower(r.get("pesel"));
                Expression<String> employmentDate = r.get("employmentDate");
                Expression<String> dismissalDate = r.get("dismissalDate");
                return b.and(
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(0) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(emailLower, "%" + searchWords.get(0) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(0) + "%"),
                                b.like(pesel, "%" + searchWords.get(0) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(0) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(1) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(emailLower, "%" + searchWords.get(1) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(1) + "%"),
                                b.like(pesel, "%" + searchWords.get(1) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(1) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(2) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(emailLower, "%" + searchWords.get(2) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(2) + "%"),
                                b.like(pesel, "%" + searchWords.get(2) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(2) + "%")
                        )
                );
            };
    } else if (searchWords.size() == 4) {
            return (r, q, b) -> {
                Expression<String> nameLower = b.lower(r.get("name"));
                Expression<String> surnameLower = b.lower(r.get("surname"));
                Expression<String> positionLower = r.get("position");
                Expression<String> salary = r.get("salary");
                Expression<String> emailLower = b.lower(r.get("email"));
                Expression<String> phoneNumber = b.lower(r.get("phoneNumber"));
                Expression<String> pesel = b.lower(r.get("pesel"));
                Expression<String> employmentDate = r.get("employmentDate");
                Expression<String> dismissalDate = r.get("dismissalDate");
                return b.and(
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(0) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(emailLower, "%" + searchWords.get(0) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(0) + "%"),
                                b.like(pesel, "%" + searchWords.get(0) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(0) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(1) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(emailLower, "%" + searchWords.get(1) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(1) + "%"),
                                b.like(pesel, "%" + searchWords.get(1) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(1) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(2) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(emailLower, "%" + searchWords.get(2) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(2) + "%"),
                                b.like(pesel, "%" + searchWords.get(2) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(2) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(3).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(3) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(3) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(3) + "%"),
                                b.like(emailLower, "%" + searchWords.get(3) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(3) + "%"),
                                b.like(pesel, "%" + searchWords.get(3) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(3) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(3) + "%")
                        )
                );
            };
        } else if (searchWords.size() == 5) {
            return (r, q, b) -> {
                Expression<String> nameLower = b.lower(r.get("name"));
                Expression<String> surnameLower = b.lower(r.get("surname"));
                Expression<String> positionLower = r.get("position");
                Expression<String> salary = r.get("salary");
                Expression<String> emailLower = b.lower(r.get("email"));
                Expression<String> phoneNumber = b.lower(r.get("phoneNumber"));
                Expression<String> pesel = b.lower(r.get("pesel"));
                Expression<String> employmentDate = r.get("employmentDate");
                Expression<String> dismissalDate = r.get("dismissalDate");
                return b.and(
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(0) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(emailLower, "%" + searchWords.get(0) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(0) + "%"),
                                b.like(pesel, "%" + searchWords.get(0) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(0) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(1) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(emailLower, "%" + searchWords.get(1) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(1) + "%"),
                                b.like(pesel, "%" + searchWords.get(1) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(1) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(2) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(emailLower, "%" + searchWords.get(2) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(2) + "%"),
                                b.like(pesel, "%" + searchWords.get(2) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(2) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(3).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(3) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(3) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(3) + "%"),
                                b.like(emailLower, "%" + searchWords.get(3) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(3) + "%"),
                                b.like(pesel, "%" + searchWords.get(3) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(3) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(3) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(4).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(4) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(4) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(4) + "%"),
                                b.like(emailLower, "%" + searchWords.get(4) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(4) + "%"),
                                b.like(pesel, "%" + searchWords.get(4) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(4) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(4) + "%")
                        )
                );
            };
        } else if (searchWords.size() == 6) {
            return (r, q, b) -> {
                Expression<String> nameLower = b.lower(r.get("name"));
                Expression<String> surnameLower = b.lower(r.get("surname"));
                Expression<String> positionLower = r.get("position");
                Expression<String> salary = r.get("salary");
                Expression<String> emailLower = b.lower(r.get("email"));
                Expression<String> phoneNumber = b.lower(r.get("phoneNumber"));
                Expression<String> pesel = b.lower(r.get("pesel"));
                Expression<String> employmentDate = r.get("employmentDate");
                Expression<String> dismissalDate = r.get("dismissalDate");
                return b.and(
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(0) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(emailLower, "%" + searchWords.get(0) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(0) + "%"),
                                b.like(pesel, "%" + searchWords.get(0) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(0) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(1) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(emailLower, "%" + searchWords.get(1) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(1) + "%"),
                                b.like(pesel, "%" + searchWords.get(1) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(1) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(2) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(emailLower, "%" + searchWords.get(2) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(2) + "%"),
                                b.like(pesel, "%" + searchWords.get(2) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(2) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(3).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(3) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(3) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(3) + "%"),
                                b.like(emailLower, "%" + searchWords.get(3) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(3) + "%"),
                                b.like(pesel, "%" + searchWords.get(3) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(3) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(3) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(4).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(4) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(4) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(4) + "%"),
                                b.like(emailLower, "%" + searchWords.get(4) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(4) + "%"),
                                b.like(pesel, "%" + searchWords.get(4) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(4) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(4) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(5).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(5) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(5) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(5) + "%"),
                                b.like(emailLower, "%" + searchWords.get(5) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(5) + "%"),
                                b.like(pesel, "%" + searchWords.get(5) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(5) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(5) + "%")
                        )
                );
            };
        }else if (searchWords.size() == 7) {
            return (r, q, b) -> {
                Expression<String> nameLower = b.lower(r.get("name"));
                Expression<String> surnameLower = b.lower(r.get("surname"));
                Expression<String> positionLower = r.get("position");
                Expression<String> salary = r.get("salary");
                Expression<String> emailLower = b.lower(r.get("email"));
                Expression<String> phoneNumber = b.lower(r.get("phoneNumber"));
                Expression<String> pesel = b.lower(r.get("pesel"));
                Expression<String> employmentDate = r.get("employmentDate");
                Expression<String> dismissalDate = r.get("dismissalDate");
                return b.and(
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(0) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(emailLower, "%" + searchWords.get(0) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(0) + "%"),
                                b.like(pesel, "%" + searchWords.get(0) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(0) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(1) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(emailLower, "%" + searchWords.get(1) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(1) + "%"),
                                b.like(pesel, "%" + searchWords.get(1) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(1) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(2) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(emailLower, "%" + searchWords.get(2) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(2) + "%"),
                                b.like(pesel, "%" + searchWords.get(2) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(2) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(3).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(3) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(3) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(3) + "%"),
                                b.like(emailLower, "%" + searchWords.get(3) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(3) + "%"),
                                b.like(pesel, "%" + searchWords.get(3) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(3) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(3) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(4).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(4) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(4) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(4) + "%"),
                                b.like(emailLower, "%" + searchWords.get(4) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(4) + "%"),
                                b.like(pesel, "%" + searchWords.get(4) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(4) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(4) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(5).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(5) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(5) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(5) + "%"),
                                b.like(emailLower, "%" + searchWords.get(5) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(5) + "%"),
                                b.like(pesel, "%" + searchWords.get(5) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(5) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(5) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(6).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(6) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(6) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(6) + "%"),
                                b.like(emailLower, "%" + searchWords.get(6) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(6) + "%"),
                                b.like(pesel, "%" + searchWords.get(6) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(6) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(6) + "%")
                        )
                );
            };
        }else {
            return (r, q, b) -> {
                Expression<String> nameLower = b.lower(r.get("name"));
                Expression<String> surnameLower = b.lower(r.get("surname"));
                Expression<String> positionLower = r.get("position");
                Expression<String> salary = r.get("salary");
                Expression<String> emailLower = b.lower(r.get("email"));
                Expression<String> phoneNumber = b.lower(r.get("phoneNumber"));
                Expression<String> pesel = b.lower(r.get("pesel"));
                Expression<String> employmentDate = r.get("employmentDate");
                Expression<String> dismissalDate = r.get("dismissalDate");
                return b.and(
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(0) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(emailLower, "%" + searchWords.get(0) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(0) + "%"),
                                b.like(pesel, "%" + searchWords.get(0) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(0) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(0) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(1) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(emailLower, "%" + searchWords.get(1) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(1) + "%"),
                                b.like(pesel, "%" + searchWords.get(1) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(1) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(1) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(2) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(emailLower, "%" + searchWords.get(2) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(2) + "%"),
                                b.like(pesel, "%" + searchWords.get(2) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(2) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(2) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(3).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(3) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(3) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(3) + "%"),
                                b.like(emailLower, "%" + searchWords.get(3) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(3) + "%"),
                                b.like(pesel, "%" + searchWords.get(3) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(3) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(3) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(4).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(4) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(4) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(4) + "%"),
                                b.like(emailLower, "%" + searchWords.get(4) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(4) + "%"),
                                b.like(pesel, "%" + searchWords.get(4) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(4) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(4) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(5).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(5) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(5) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(5) + "%"),
                                b.like(emailLower, "%" + searchWords.get(5) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(5) + "%"),
                                b.like(pesel, "%" + searchWords.get(5) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(5) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(5) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(6).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(6) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(6) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(6) + "%"),
                                b.like(emailLower, "%" + searchWords.get(6) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(6) + "%"),
                                b.like(pesel, "%" + searchWords.get(6) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(6) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(6) + "%")
                        ),
                        b.or(
                                b.like(nameLower, "%" + searchWords.get(7).toLowerCase() + "%"),
                                b.like(surnameLower, "%" + searchWords.get(7) + "%"),
                                b.like(positionLower.as(String.class), "%" + searchWords.get(7) + "%"),
                                b.like(salary.as(String.class), "%" + searchWords.get(7) + "%"),
                                b.like(emailLower, "%" + searchWords.get(7) + "%"),
                                b.like(phoneNumber, "%" + searchWords.get(7) + "%"),
                                b.like(pesel, "%" + searchWords.get(7) + "%"),
                                b.like(employmentDate.as(String.class), "%" + searchWords.get(7) + "%"),
                                b.like(dismissalDate.as(String.class), "%" + searchWords.get(7) + "%")
                        )
                );
            };
        }
    }
}