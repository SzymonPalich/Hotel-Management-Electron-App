package com.spurvago.components;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface IBaseController<T> {
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    T find(@PathVariable Long id);

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ListPaginated<T> getList(Pager pager, String search);

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    T create(@RequestBody T newTestEntity);

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    T update(@PathVariable Long id, @RequestBody T newTestEntity);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id);
}
