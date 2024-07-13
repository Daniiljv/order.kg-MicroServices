package my.code.main.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProductPositionDto {

    private Long id;

    private Long orderId;

    private Long establishmentId;

    private Long productId;

    private Long productQuantity;

}
