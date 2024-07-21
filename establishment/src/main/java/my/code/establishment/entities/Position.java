package my.code.establishment.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "positions_seq_generator")
    @SequenceGenerator(name = "positions_seq_generator", sequenceName = "positions_seq", allocationSize = 1)
    private Long id;

    private String name;

    @ManyToOne
    private Establishment establishment;

    private String createdBy;
    private Timestamp createdAt;

    private String lastModifiedBy;
    private Timestamp lastModifiedAt;

    private String deletedBy;
    private Timestamp deletedAt;
}
