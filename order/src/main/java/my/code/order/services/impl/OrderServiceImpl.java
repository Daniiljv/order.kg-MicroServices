package my.code.order.services.impl;

import lombok.RequiredArgsConstructor;
import my.code.order.entities.Order;
import my.code.order.entities.ProductPosition;
import my.code.order.repositories.OrderRepo;
import my.code.order.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepo orderRepo;

    @Override
    public Long createOrder(List<ProductPosition> products) {
        return orderRepo.save(new Order(null, products)).getId();
    }


}
