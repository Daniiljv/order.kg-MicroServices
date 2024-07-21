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

public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "establishment_seq_generator")
    @SequenceGenerator(name = "establishment_seq_generator", sequenceName = "establishment_seq", allocationSize = 1)
    private Long id;

    @NotNull(message = "Establishment name is required")
    private String name;
}
