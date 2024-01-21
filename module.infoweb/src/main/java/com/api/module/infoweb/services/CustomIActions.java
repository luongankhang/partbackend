package com.api.module.infoweb.services;

import com.api.module.infoweb.responses.CustomResponse;

import java.util.UUID;

public interface CustomIActions<T> {
    CustomResponse create(T t);
    CustomResponse update(UUID id, T t);
    CustomResponse delete(UUID id);
}
