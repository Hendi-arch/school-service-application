package com.hendi.schoolservice.infrastructure.config.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hendi.schoolservice.infrastructure.config.db.schema.AttendanceSchema;

public interface AttendanceRepository extends JpaRepository<AttendanceSchema, Long> {
    // Add custom query methods if needed
}
