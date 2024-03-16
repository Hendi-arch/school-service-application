package com.hendi.schoolservice.infrastructure.config.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hendi.schoolservice.infrastructure.config.db.schema.SubmissionSchema;

public interface SubmissionRepository extends JpaRepository<SubmissionSchema, Long> {
    // Add custom query methods if needed
}
