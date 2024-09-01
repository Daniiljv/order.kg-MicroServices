package my.code.common.dtos;

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

    private String locationCity;

    private String address;

    private Long ownerId;


}
