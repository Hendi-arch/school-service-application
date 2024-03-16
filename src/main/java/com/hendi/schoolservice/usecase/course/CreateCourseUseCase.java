package com.hendi.schoolservice.usecase.course;

import com.hendi.schoolservice.entity.course.gateway.CourseGateway;
import com.hendi.schoolservice.entity.course.model.CourseModel;
import com.hendi.schoolservice.entity.user.exception.UserNotFoundException;
import com.hendi.schoolservice.entity.user.gateway.UserGateway;
import com.hendi.schoolservice.entity.user.model.UserAccountModel;
import com.hendi.schoolservice.usecase.course.dto.ICourseCreateData;

public class CreateCourseUseCase {

    private final CourseGateway courseGateway;
    private final UserGateway userGateway;

    public CreateCourseUseCase(
            CourseGateway courseGateway,
            UserGateway userGateway) {
        this.courseGateway = courseGateway;
        this.userGateway = userGateway;
    }

    public CourseModel execute(ICourseCreateData data) throws UserNotFoundException {
        Long teacherId = data.teacherId();
        String name = data.name();
        String description = data.description();

        UserAccountModel teacherData = userGateway.findById(teacherId).orElseThrow(UserNotFoundException::new);
        CourseModel courseData = new CourseModel(teacherData, name, description);
        return courseGateway.create(courseData);
    }

}
