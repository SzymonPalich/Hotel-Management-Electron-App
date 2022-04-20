package com.spurvago.components;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

public interface IBaseController<T> {
    @GetMapping(path = "/{id}")
    T find(@PathVariable Long id);

    @GetMapping(params = {"index", "size", "sort"})
    Page<T> getList(@RequestParam("index") int index, @RequestParam("size") int size, @RequestParam("sort") String sort);

    @PostMapping()
    T create(@RequestBody T newTestEntity);

    @PutMapping(path = "/{id}")
    T update(@PathVariable Long id, @RequestBody T newTestEntity);

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable Long id);
}
