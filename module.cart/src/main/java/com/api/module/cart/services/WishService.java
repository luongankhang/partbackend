package com.api.module.cart.services;

import com.api.module.cart.dtos.WishDto;
import com.api.module.cart.models.Wish;
import com.api.module.cart.repositories.WishRepository;
import com.api.module.cart.responses.CustomResponse;
import com.api.module.cart.responses.ValidationUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WishService implements CustomIActions<WishDto> {
    private final WishRepository wishRepository;

    @Override
    public CustomResponse create(WishDto wishDto) {
        var obj = Wish.builder()
                .user(wishDto.getUser())
                .product(wishDto.getProduct())
                .build();

        var saved = wishRepository.save(obj);

        return CustomResponse.builder().data(saved).build();
    }

    @Transactional
    @Override
    public CustomResponse deleteAll(String user) {
        wishRepository.deleteByUser(user);
        return CustomResponse.builder().message("OK").build();
    }

    @Override
    public CustomResponse delete(UUID id) {
        if (ValidationUtils.existsFormById(id)) {
            final var wish = wishRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Wish not found."));

            wishRepository.delete(wish);
            return CustomResponse.builder().message("OK").build();
        }
        return CustomResponse.builder().message("Invalid UUID format for id.").build();
    }

    @Transactional
    public CustomResponse deleteWish(String user, String product) {
        wishRepository.deleteByUserAndProduct(user, product);

        return CustomResponse.builder().message("OK").build();
    }
}
