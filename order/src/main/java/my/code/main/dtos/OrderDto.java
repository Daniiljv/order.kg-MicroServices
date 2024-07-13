package my.code.main.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.code.main.entities.ProductPosition;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OrderDto {

    private Long id;

    private List<ProductPosition> productPositions;
}
