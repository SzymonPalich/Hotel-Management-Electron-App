package com.spurvago.server.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping(path = "/api/test")
public class TestController {
    private final TestService testService;

    @Autowired
    TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(path = "/{id}")
    TestEntity find(@PathVariable Long id) {

        TestEntity test = testService.find(id);
        if (test == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        return test;
    }

    @GetMapping()
    List<TestEntity> getList() {
        List<TestEntity> test = testService.getList();

        return test;
    }

    @PostMapping()
    TestEntity create(@RequestBody TestEntity newTestEntity) {
        TestEntity test = testService.create(newTestEntity);

        return test;
    }

    @PutMapping(path = "/{id}")
    TestEntity update(@PathVariable Long id, @RequestBody TestEntity newTestEntity) {

        TestEntity test = testService.find(id);
        if (test == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        test = testService.update(test, newTestEntity);

        return test;
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable Long id) {

        TestEntity test = testService.find(id);
        if (test == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        testService.delete(test);
    }
}
