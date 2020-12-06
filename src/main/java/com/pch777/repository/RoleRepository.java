package com.pch777.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pch777.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{

}
