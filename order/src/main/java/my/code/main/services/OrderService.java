package my.code.main.services;

import my.code.main.entities.ProductPosition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    Long createOrder(List<ProductPosition> products);

}
