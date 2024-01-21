package com.api.module.infoweb.services;

import com.api.module.infoweb.dtos.InformationAboutDto;
import com.api.module.infoweb.models.InformationAbout;
import com.api.module.infoweb.repositories.InformationAboutRepository;
import com.api.module.infoweb.responses.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InformationAboutService implements CustomIActions<InformationAboutDto> {
    private final InformationAboutRepository informationAboutRepository;

    @Override
    public CustomResponse create(InformationAboutDto informationAboutDto) {
        var obj = InformationAbout.builder()
                .content(informationAboutDto.getContent())
                .hide(informationAboutDto.getHide())
                .build();

        var saved = informationAboutRepository.save(obj);

        return CustomResponse.builder().data(saved).build();
    }

    @Override
    public CustomResponse update(UUID id, InformationAboutDto informationAboutDto) {
        return null;
    }

    @Override
    public CustomResponse delete(UUID id) {
        return null;
    }
}
