package my.code.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.code.common.enums.EstablishmentRequestType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentResponseDto<T>{

    private T data;


}
