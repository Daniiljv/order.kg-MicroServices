package my.code.establishment.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "establishment_seq_generator")
    @SequenceGenerator(name = "establishment_seq_generator", sequenceName = "establishment_seq", allocationSize = 1)
    private Long id;

    private String logoImage;

    private String name;

    private String location;

    private Long ownerId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "establishment")
    private List<Position> positions = new ArrayList<>();

    private String createdBy;
    private Timestamp createdAt;

    private String lastModifiedBy;
    private Timestamp lastModifiedAt;

    private String deletedBy;
    private Timestamp deletedAt;

}
