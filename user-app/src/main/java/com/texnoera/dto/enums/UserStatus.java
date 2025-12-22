package com.texnoera.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum UserStatus {

    ACTIVE(1),
    INACTIVE(0);

    private final Integer value;

    public static UserStatus fromValue(Integer value) {
        return Arrays.stream(UserStatus.values())
                .filter(val -> val.getValue().equals(value))
                .findFirst()
                .orElse(INACTIVE);
    }

}
