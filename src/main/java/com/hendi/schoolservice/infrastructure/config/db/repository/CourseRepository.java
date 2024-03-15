package com.hendi.schoolservice.infrastructure.config.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hendi.schoolservice.infrastructure.config.db.schema.CourseSchema;

public interface CourseRepository extends JpaRepository<CourseSchema, Long> {
    // Add custom query methods if needed
}
