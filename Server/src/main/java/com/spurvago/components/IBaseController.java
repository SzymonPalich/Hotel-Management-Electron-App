package com.spurvago.components;

import org.springframework.web.bind.annotation.*;

public interface IBaseController<T> {
    @GetMapping(path = "/{id}")
    T find(@PathVariable Long id);

    @GetMapping()
    ListPaginated<T> getList(Pager pager);

    @PostMapping()
    T create(@RequestBody T newTestEntity);

    @PutMapping(path = "/{id}")
    T update(@PathVariable Long id, @RequestBody T newTestEntity);

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable Long id);
}
