package com.spurvago.components;

import java.util.List;

public interface IBaseService<T> {
    T find(long id);

    List<T> getList();

    T create(T newTestEntity);

    T update(T oldTestEntity, T newTestEntity);

    void delete(T testEntity);
}
