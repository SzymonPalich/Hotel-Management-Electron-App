package com.spurvago.server.test;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository
        extends CrudRepository<TestEntity, Long> {

    TestEntity findById(long id);

    List<TestEntity> findAll();


}
