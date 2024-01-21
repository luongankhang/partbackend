package com.api.module.cart.services;

import com.api.module.cart.responses.CustomResponse;

import java.util.UUID;

public interface CustomIActions<T> {
    CustomResponse create(T t);

    CustomResponse deleteAll(String user);

    CustomResponse delete(UUID id);
}

