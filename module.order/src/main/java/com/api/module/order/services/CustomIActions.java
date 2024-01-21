package com.api.module.order.services;

import com.api.module.order.responses.CustomResponse;

import java.util.UUID;

public interface CustomIActions<T> {
    CustomResponse create(T t);

    CustomResponse delete(UUID id);
}
