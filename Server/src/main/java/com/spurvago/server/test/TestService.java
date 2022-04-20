package com.spurvago.server.test;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record TestService(TestRepository testRepository) {

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
