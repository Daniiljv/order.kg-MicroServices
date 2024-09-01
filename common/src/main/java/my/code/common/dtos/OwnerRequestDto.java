package my.code.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.code.common.enums.EstablishmentRequestType;
import my.code.common.enums.OwnerRequestType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRequestDto<T> {

    private OwnerRequestType ownerRequestType;

    private T data;


}
