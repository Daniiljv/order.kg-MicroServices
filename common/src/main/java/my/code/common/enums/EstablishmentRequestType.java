package my.code.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstablishmentRequestType {

    GET_BY_ID("Get establishment by id"),
    GET_ALL_ACTIVE("Get all active establishments");

    private final String description;
}
