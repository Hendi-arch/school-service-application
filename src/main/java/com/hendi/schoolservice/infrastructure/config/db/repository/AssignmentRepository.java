package com.hendi.schoolservice.infrastructure.config.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hendi.schoolservice.infrastructure.config.db.schema.AssignmentSchema;

public interface AssignmentRepository extends JpaRepository<AssignmentSchema, Long> {
    // Add custom query methods if needed
}
