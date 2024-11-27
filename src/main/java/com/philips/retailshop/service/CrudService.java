package com.philips.retailshop.service;

import java.util.List;

public interface CrudService<T> {
    T create(T dto);
    T getById(Long id) throws Exception;
    List<T> getAll();
    T update(Long id, T dto) throws Exception;
    void delete(Long id) throws Exception;
}
