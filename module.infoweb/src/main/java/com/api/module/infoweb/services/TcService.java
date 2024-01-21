package com.api.module.infoweb.services;

import com.api.module.infoweb.dtos.TcDto;
import com.api.module.infoweb.models.Tc;
import com.api.module.infoweb.repositories.TcRepository;
import com.api.module.infoweb.responses.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TcService implements CustomIActions<TcDto> {
    private final TcRepository tcRepository;

    @Override
    public CustomResponse create(TcDto tcDto) {
        var obj = Tc.builder()
                .content(tcDto.getContent())
                .build();

        var saved = tcRepository.save(obj);

        return CustomResponse.builder().data(saved).build();
    }

    @Override
    public CustomResponse update(UUID id, TcDto tcDto) {
        return null;
    }

    @Override
    public CustomResponse delete(UUID id) {
        return null;
    }
}
