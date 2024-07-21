package my.code.order.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class ProductPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_position_seq_generator")
    @SequenceGenerator(name = "product_position_seq_generator", sequenceName = "product_position_count_seq", allocationSize = 1)
    private Long id;

    private Long orderId;

    @NotNull(message = "Establishment id is required")
    private Long establishmentId;

    @NotNull(message = "Product id is required")
    private Long productId;

    @NotNull(message = "Product quantity is required")
    private Long productQuantity;
}
