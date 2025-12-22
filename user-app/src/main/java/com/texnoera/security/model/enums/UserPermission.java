package com.texnoera.security.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserPermission {

    READ_USERS("users:read"),
    WRITE_USERS("users:write");

    private final String permission;

}
