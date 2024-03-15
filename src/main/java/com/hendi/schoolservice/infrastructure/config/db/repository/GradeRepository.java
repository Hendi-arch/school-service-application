package com.hendi.schoolservice.infrastructure.config.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hendi.schoolservice.infrastructure.config.db.schema.GradeSchema;

public interface GradeRepository extends JpaRepository<GradeSchema, Long> {
    // Add custom query methods if needed
}
