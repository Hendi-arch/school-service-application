package com.hendi.schoolservice.usecase.course;

import com.hendi.schoolservice.entity.course.exception.CourseNotFoundException;
import com.hendi.schoolservice.entity.course.gateway.CourseGateway;
import com.hendi.schoolservice.entity.course.model.CourseModel;

public class GetCourseUseCase {

    private final CourseGateway courseGateway;

    public GetCourseUseCase(CourseGateway courseGateway) {
        this.courseGateway = courseGateway;
    }

    public CourseModel execute(Long id) throws CourseNotFoundException {
        return courseGateway
                .findById(id)
                .orElseThrow(CourseNotFoundException::new);
    }

}
