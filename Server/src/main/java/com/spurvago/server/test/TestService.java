package com.spurvago.server.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public TestEntity find(long id) {
        return testRepository.findById(id);
    }

    public List<TestEntity> getList() {
        return testRepository.findAll();
    }

    public TestEntity create(TestEntity newTestEntity) {
        return testRepository.save(newTestEntity);
    }

    public TestEntity update(TestEntity oldTestEntity, TestEntity newTestEntity) {
        oldTestEntity.map(newTestEntity);

        return testRepository.save(oldTestEntity);
    }

    public void delete(TestEntity testEntity) {
        testRepository.delete(testEntity);
    }
}
