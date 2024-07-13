package my.code.main.services.impl;

import lombok.RequiredArgsConstructor;
import my.code.main.entities.Order;
import my.code.main.entities.ProductPosition;
import my.code.main.repositories.OrderRepo;
import my.code.main.services.OrderService;
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
