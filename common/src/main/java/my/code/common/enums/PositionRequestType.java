package my.code.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PositionRequestType {

    GET_BY_ID("Get position by id"),
    GET_ALL_BY_ESTABLISHMENT_ID("Get all by establishment id"),;

    private final String description;
}
