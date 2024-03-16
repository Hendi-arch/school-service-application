package com.hendi.schoolservice.usecase.course;

import java.util.List;

import com.hendi.schoolservice.entity.course.gateway.CourseGateway;
import com.hendi.schoolservice.entity.course.model.CourseModel;

public class SearchCourseUseCase {

    private final CourseGateway courseGateway;

    public SearchCourseUseCase(CourseGateway courseGateway) {
        this.courseGateway = courseGateway;
    }

    public List<CourseModel> execute() {
        return courseGateway.findAll();
    }

}
