package com.hendi.schoolservice.infrastructure.config.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hendi.schoolservice.infrastructure.config.db.schema.UserRoleSchema;

public interface UserRoleRepository extends JpaRepository<UserRoleSchema, Long> {
    // Add custom query methods if needed
}
