package com.hammad.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hammad.backend.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    boolean existsByUsername(String username);
}
