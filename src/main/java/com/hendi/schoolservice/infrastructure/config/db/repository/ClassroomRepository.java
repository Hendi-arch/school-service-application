package com.hendi.schoolservice.infrastructure.config.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hendi.schoolservice.infrastructure.config.db.schema.ClassroomSchema;

public interface ClassroomRepository extends JpaRepository<ClassroomSchema, Long> {
    // Add custom query methods if needed
}
