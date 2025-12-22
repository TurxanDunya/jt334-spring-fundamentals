package com.texnoera.springlesson.model.enums;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.texnoera.springlesson.model.enums.UserPermission.CHANGE_SETTINGS;
import static com.texnoera.springlesson.model.enums.UserPermission.READ_STUDENTS;
import static com.texnoera.springlesson.model.enums.UserPermission.WRITE_STUDENTS;

@Getter
@AllArgsConstructor
public enum UserRole {

    USER(Sets.newHashSet(READ_STUDENTS)),
    ADMIN(Sets.newHashSet(READ_STUDENTS, WRITE_STUDENTS, CHANGE_SETTINGS));

    private final Set<UserPermission> userPermissions;

    public Set<GrantedAuthority> getGrantedAuthorities() {
        return getUserPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

}
