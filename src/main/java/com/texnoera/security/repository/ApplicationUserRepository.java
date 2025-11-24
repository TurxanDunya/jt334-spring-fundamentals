package com.texnoera.security.repository;

import com.texnoera.security.model.ApplicationUser;
import com.texnoera.security.model.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class ApplicationUserRepository {

    private final PasswordEncoder passwordEncoder;

    public Optional<ApplicationUser> findByUsername(String username) {
        ApplicationUser user = new ApplicationUser(
                UserRole.USER.getGrantedAuthorities().stream().toList(),
                "jane",
                passwordEncoder.encode("12345"),
                true,
                true,
                true,
                true);

        ApplicationUser admin = new ApplicationUser(
                UserRole.ADMIN.getGrantedAuthorities().stream().toList(),
                "jack",
                passwordEncoder.encode("1234"),
                true,
                true,
                true,
                true);

        return Stream.of(user, admin)
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

}
