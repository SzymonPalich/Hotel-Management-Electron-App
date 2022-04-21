package com.spurvago.components;

import org.springframework.data.domain.Page;

import java.util.List;

public class ListPaginated<T> {
    public List<T> content;
    public Pager pager;
    public long totalElements;
    public int totalPages;

    public ListPaginated(List<T> content, Pager pager, long totalElements, int totalPages) {
        this.content = content;
        this.pager = pager;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public ListPaginated(Page<T> entities, Pager pager) {
        this.content = entities.getContent();
        this.pager = pager;
        this.totalElements = entities.getTotalElements();
        this.totalPages = entities.getTotalPages();
    }
}
