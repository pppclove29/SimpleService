package com.example.demo.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Member;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByName(String name);
}
