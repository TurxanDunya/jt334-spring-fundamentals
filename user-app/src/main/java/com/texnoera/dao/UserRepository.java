package com.texnoera.dao;

import com.texnoera.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByIdAndName(Long id, String username);

    List<User> findByName(String name);

    @Query("select u from users u where u.surname=:lastName")
    List<User> findByLastName(String lastName);

}
