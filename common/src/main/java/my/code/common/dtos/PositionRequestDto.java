package my.code.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.code.common.enums.PositionRequestType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PositionRequestDto<T> {

    private PositionRequestType positionRequestType;

    private T data;
}
