package com.spurvago.components;

import org.springframework.data.domain.Page;


public interface IBaseService<T> {
    T find(long id);

    Page<T> getList(Pager pager);

    T create(T newTestEntity);

    T update(T oldTestEntity, T newTestEntity);

    void delete(T testEntity);
}
