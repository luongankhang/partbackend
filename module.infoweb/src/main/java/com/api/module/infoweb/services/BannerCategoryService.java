package com.api.module.infoweb.services;

import com.api.module.infoweb.dtos.BannerCategoryDto;
import com.api.module.infoweb.models.BannerCategory;
import com.api.module.infoweb.repositories.BannerCategoryRepository;
import com.api.module.infoweb.responses.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BannerCategoryService implements CustomIActions<BannerCategoryDto> {
    private final BannerCategoryRepository bannerCategoryRepository;

    @Override
    public CustomResponse create(BannerCategoryDto bannerCategoryDto) {
        var obj = BannerCategory.builder()
                .fileName(bannerCategoryDto.getFileName())
                .hide(bannerCategoryDto.getHide())
                .build();

        var saved = bannerCategoryRepository.save(obj);

        return CustomResponse.builder().data(saved).build();
    }

    @Override
    public CustomResponse update(UUID id, BannerCategoryDto bannerCategoryDto) {
        return null;
    }

    @Override
    public CustomResponse delete(UUID id) {
        return null;
    }
}
