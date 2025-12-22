package com.texnoera.springlesson.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserPermission {

    READ_STUDENTS("student:read"),
    WRITE_STUDENTS("student:write"),
    CHANGE_SETTINGS("settings:change");

    private final String permission;


}
