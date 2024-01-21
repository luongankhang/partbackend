package com.api.module.product.services;

import com.api.module.product.responses.CustomResponse;

import java.util.UUID;

public interface CustomIActions<T> {
    CustomResponse listAll();
    CustomResponse getById(UUID id);
    CustomResponse create(T t);
    CustomResponse update(UUID id, T t);
    CustomResponse delete(UUID id);
}
