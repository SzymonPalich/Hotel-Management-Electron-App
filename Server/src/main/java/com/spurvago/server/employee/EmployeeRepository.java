package com.spurvago.server.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>, CrudRepository<Employee, Long> {

    Employee findById(long id);

    Page<Employee> findAll(Pageable pageable);

    @Query("SELECT e FROM Employee e Where e.email = :email")
    Employee getEmployeeByEmail(@Param("email") String email);
}
