package com.spurvago.components;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Komponent do tworzenia Paginacji
 */
public class Pager {
    public int index;
    public int size;
    public String sort;

    /**
     * Paginacja z domyślną liczbą rekordów wynoszącą 10
     * @param index Numer strony, zaczynają się od zera
     * @param sort  Nazwa kolumny, po której będzie się sortowało
     */
    public Pager(int index, String sort) {
        this.index = index;
        this.size = 10;
        this.sort = sort;
    }

    /**
     *
     * @param index Numer strony
     * @param size  Liczba rekordów na stronę
     * @param sort  Nazwa kolumny, po której będzie się sortowało
     */
    public Pager(int index, int size, String sort) {
        this.index = index;
        this.size = size;
        this.sort = sort;
    }

    public Pageable getPageable() {
        return PageRequest.of(this.index, this.size, Sort.by(this.sort)).previousOrFirst();
    }
}
