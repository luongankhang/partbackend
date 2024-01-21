package com.api.module.infoweb.services;

import com.api.module.infoweb.dtos.AboutDto;
import com.api.module.infoweb.models.About;
import com.api.module.infoweb.repositories.AboutRepository;
import com.api.module.infoweb.responses.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AboutService implements CustomIActions<AboutDto> {
    private final AboutRepository aboutRepository;

    @Override
    public CustomResponse create(AboutDto aboutDto) {
        var obj = About.builder()
                .content(aboutDto.getContent())
                .fileName(aboutDto.getFileName())
                .hide(aboutDto.getHide())
                .build();

        var saved = aboutRepository.save(obj);

        return CustomResponse.builder().data(saved).build();
    }

    @Override
    public CustomResponse update(UUID id, AboutDto aboutDto) {
        return null;
    }

    @Override
    public CustomResponse delete(UUID id) {
        return null;
    }
}
