package com.api.module.infoweb.services;

import com.api.module.infoweb.dtos.BannerDto;
import com.api.module.infoweb.models.Banner;
import com.api.module.infoweb.repositories.BannerRepository;
import com.api.module.infoweb.responses.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BannerService implements CustomIActions<BannerDto> {
    private final BannerRepository bannerRepository;

    @Override
    public CustomResponse create(BannerDto bannerDto) {
        var obj = Banner.builder()
                .fileName1(bannerDto.getFileName1())
                .fileName2(bannerDto.getFileName2())
                .hide(bannerDto.getHide())
                .build();

        var saved = bannerRepository.save(obj);

        return CustomResponse.builder().data(saved).build();
    }

    @Override
    public CustomResponse update(UUID id, BannerDto bannerDto) {
        return null;
    }

    @Override
    public CustomResponse delete(UUID id) {
        return null;
    }
}
