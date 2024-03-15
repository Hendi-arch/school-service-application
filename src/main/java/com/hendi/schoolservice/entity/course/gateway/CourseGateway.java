package com.hendi.schoolservice.entity.course.gateway;

import java.util.List;
import java.util.Optional;

import com.hendi.schoolservice.entity.course.model.CourseModel;

public interface CourseGateway {

    CourseModel create(CourseModel courseModel);

    CourseModel update(CourseModel courseModel);

    void delete(Long id);

    Optional<CourseModel> findById(Long id);

    List<CourseModel> findAll();

}
