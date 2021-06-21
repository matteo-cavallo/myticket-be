package com.matteo.myticket.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CRUDService<T> {

    T add(T t);
    List<T> findAll();
    Optional<T> findById(Integer id);
}
