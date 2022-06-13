package com.spurvago.components;

import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Komponent do tworzenia Paginacji
 */
public class Pager {
    @ApiParam(value = "index")
    public Integer index;
    @ApiParam(value = "size")
    public Integer size;
    @ApiParam(value = "sort")
    public String sort;

    /**
     * @param index Numer strony
     * @param size  Liczba rekordów na stronę
     * @param sort  Nazwa kolumny, po której będzie się sortowało
     */
    public Pager(Integer index, Integer size, String sort) {
        this.index = (index == null) ? 1 : index;
        this.size = (size == null) ? 10 : size;
        this.sort = (Utils.isNullOrBlank(sort)) ? "id" : sort;
    }

    public Pageable makePageable() {
        return PageRequest.of(this.index, this.size, Sort.by(this.sort).descending()).previousOrFirst();
    }
}
