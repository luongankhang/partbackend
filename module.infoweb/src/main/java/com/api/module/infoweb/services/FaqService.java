package com.api.module.infoweb.services;

import com.api.module.infoweb.dtos.FaqDto;
import com.api.module.infoweb.models.Faq;
import com.api.module.infoweb.repositories.FaqRepository;
import com.api.module.infoweb.responses.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FaqService implements CustomIActions<FaqDto> {
    private final FaqRepository faqRepository;

    @Override
    public CustomResponse create(FaqDto faqDto) {
        var obj = Faq.builder()
                .content(faqDto.getContent())
                .subContent(faqDto.getSubContent())
                .build();

        var saved = faqRepository.save(obj);

        return CustomResponse.builder().data(saved).build();
    }

    @Override
    public CustomResponse update(UUID id, FaqDto faqDto) {
        return null;
    }

    @Override
    public CustomResponse delete(UUID id) {
        return null;
    }
}
