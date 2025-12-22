package com.texnoera.security.repository;

import com.texnoera.security.repository.entity.ApplicationUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUserEntity, Long> {

    Optional<ApplicationUserEntity> findByUsername(String username);

}
