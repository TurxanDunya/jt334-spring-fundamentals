package com.texnoera.security.model.enums;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.texnoera.security.model.enums.UserPermission.READ_USERS;
import static com.texnoera.security.model.enums.UserPermission.WRITE_USERS;

@Getter
@AllArgsConstructor
public enum UserRole {

    USER(Sets.newHashSet(READ_USERS)),
    ADMIN(Sets.newHashSet(READ_USERS, WRITE_USERS));

    private final Set<UserPermission> userPermissions;

    public Set<GrantedAuthority> getGrantedAuthorities() {
        return getUserPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

}
