package com.texnoera.security.service;

import com.texnoera.security.repository.ApplicationUserRepository;
import com.texnoera.security.repository.entity.ApplicationUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUserEntity applicationUserEntity = applicationUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));


        return User.builder()
                .username(applicationUserEntity.getUsername())
                .password(applicationUserEntity.getPassword())
                .authorities(applicationUserEntity.getRole().getGrantedAuthorities().stream().toList())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
