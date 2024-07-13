package my.code.establishment.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CreateEstablishmentDto {

    private String name;

    private String location;

    private Long ownerId;

}
