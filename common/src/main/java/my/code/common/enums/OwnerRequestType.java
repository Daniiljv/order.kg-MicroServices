package my.code.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OwnerRequestType {

    REGISTER("Register new Owner"),
    GET_BY_ID("Get owner by id"),
    GET_ALL_ACTIVE("Get all active owners"),
    ADD_ESTABLISHMENT("Add establishment");

    private final String description;
}
