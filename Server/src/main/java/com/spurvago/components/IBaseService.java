package com.spurvago.components;

public interface IBaseService<T> {
    T find(long id);

    ListPaginated<T> getList(Pager pager);

    T create(T newTestEntity);

    T update(T oldTestEntity, T newTestEntity);

    void delete(T testEntity);
}
