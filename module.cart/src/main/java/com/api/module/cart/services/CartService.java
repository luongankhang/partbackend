package com.api.module.cart.services;

import com.api.module.cart.dtos.CartDto;
import com.api.module.cart.models.Cart;
import com.api.module.cart.repositories.CartRepository;
import com.api.module.cart.responses.CustomResponse;
import com.api.module.cart.responses.ValidationUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService implements CustomIActions<CartDto> {
    private final CartRepository cartRepository;

    @Override
    public CustomResponse create(CartDto cartDto) {
        var obj = Cart.builder()
                .user(cartDto.getUser())
                .product(cartDto.getProduct())
                .build();
        var saved = cartRepository.save(obj);

        return CustomResponse.builder().data(saved).build();
    }

    @Transactional
    @Override
    public CustomResponse deleteAll(String user) {
        cartRepository.deleteByUser(user);
        return CustomResponse.builder().message("OK").build();
    }

    @Override
    public CustomResponse delete(UUID id) {
        if (ValidationUtils.existsFormById(id)) {
            final var cart = cartRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Cart not found."));

            cartRepository.delete(cart);
            return CustomResponse.builder().message("OK").build();
        }
        return CustomResponse.builder().message("Invalid UUID format for id.").build();
    }

    @Transactional
    public CustomResponse deleteCart(String user, String product) {
        cartRepository.deleteByUserAndProduct(user, product);

        return CustomResponse.builder().message("OK").build();
    }
}
