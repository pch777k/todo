package com.pch777.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pch777.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
