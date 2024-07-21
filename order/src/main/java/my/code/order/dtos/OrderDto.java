package my.code.order.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.code.order.entities.ProductPosition;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OrderDto {

    private Long id;

    private List<ProductPosition> productPositions;
}
