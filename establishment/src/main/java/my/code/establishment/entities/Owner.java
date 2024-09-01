package my.code.establishment.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owner_seq_generator")
    @SequenceGenerator(name = "owner_seq_generator", sequenceName = "owner_seq", allocationSize = 1)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    @OneToMany
    private List<Establishment> establishments = new ArrayList<>();
}
