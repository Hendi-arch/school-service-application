package com.hendi.schoolservice.infrastructure.course.gateway;

import java.util.List;
import java.util.Optional;

import com.hendi.schoolservice.entity.course.gateway.CourseGateway;
import com.hendi.schoolservice.entity.course.model.CourseModel;
import com.hendi.schoolservice.infrastructure.config.db.repository.CourseRepository;
import com.hendi.schoolservice.infrastructure.config.db.schema.CourseSchema;

public class CourseDatabaseGateway implements CourseGateway {

    private final CourseRepository repository;

    public CourseDatabaseGateway(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public CourseModel create(CourseModel courseModel) {
        return repository.save(new CourseSchema(courseModel)).toCourseModel();
    }

    @Override
    public CourseModel update(CourseModel courseModel) {
        return repository.save(new CourseSchema(courseModel)).toCourseModel();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<CourseModel> findById(Long id) {
        return repository.findById(id).map(CourseSchema::toCourseModel);
    }

    @Override
    public List<CourseModel> findAll() {
        return repository.findAll().stream().map(CourseSchema::toCourseModel).toList();
    }

    @Override
    public List<CourseModel> createAll(List<CourseModel> courseModel) {
        return repository.saveAll(CourseSchema.toCourseSchemaList(courseModel)).stream()
                .map(CourseSchema::toCourseModel).toList();
    }

}
