package my.code.order.services;

import my.code.order.entities.ProductPosition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    Long createOrder(List<ProductPosition> products);

}
