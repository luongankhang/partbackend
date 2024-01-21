package com.api.module.order.services;

import com.api.module.order.dtos.OrderDto;
import com.api.module.order.models.Order;
import com.api.module.order.models.OrderState;
import com.api.module.order.models.ProductInfo;
import com.api.module.order.repositories.OrderRepository;
import com.api.module.order.repositories.OrderStateRepository;
import com.api.module.order.responses.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements CustomIActions<OrderDto> {
    private final OrderRepository orderRepository;
    private final OrderStateRepository orderStateRepository;

    @Override
    public CustomResponse create(OrderDto orderDto) {
        try {
            // Tạo đối tượng Order từ OrderDto
            Order order = new Order();
            order.setUser(orderDto.getUser());
            order.setFirstName(orderDto.getFirstName());
            order.setLastName(orderDto.getLastName());
            order.setAddress(orderDto.getAddress());
            order.setEmail(orderDto.getEmail());
            order.setPhone(orderDto.getPhone());
            order.setNote(orderDto.getNote());

            // Tạo danh sách ProductInfo từ ProductInfoDto
            List<ProductInfo> productInfos = orderDto.getProducts().stream()
                    .map(productInfoDto -> {
                        ProductInfo productInfo = new ProductInfo();
                        productInfo.setProductName(productInfoDto.getProductName());
                        productInfo.setQuantity(productInfoDto.getQuantity());
                        // Nếu cần thêm các trường khác, hãy thêm vào đây

                        // Liên kết ProductInfo với Order
                        productInfo.setOrderRequest(order);

                        return productInfo;
                    })
                    .collect(Collectors.toList());

            // Liên kết danh sách ProductInfo với Order
            order.setProducts(productInfos);

            // Lưu Order vào cơ sở dữ liệu
            orderRepository.save(order);
            OrderState orderState = new OrderState();
            orderState.setOrder(order);
            orderState.setState("Đã đặt hàng");
            orderState.setDate(LocalDate.now());

            orderStateRepository.save(orderState);

            return CustomResponse.builder().message("Đã tạo đơn hàng thành công").build();
        } catch (Exception e) {
            return CustomResponse.builder().message("Lỗi khi tạo đơn hàng: " + e.getMessage()).build();
        }
    }

    @Override
    public CustomResponse delete(UUID id) {
        return null;
    }
}
