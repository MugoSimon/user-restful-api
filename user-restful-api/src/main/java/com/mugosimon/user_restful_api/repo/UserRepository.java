package com.mugosimon.user_restful_api.repo;

import com.mugosimon.user_restful_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.firstName = :name OR u.lastName = :name")
    List<User> findByFirstnameOrLastname(@Param("name") String name);
}
