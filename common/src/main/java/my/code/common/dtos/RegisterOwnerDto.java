package my.code.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterOwnerDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;
}
