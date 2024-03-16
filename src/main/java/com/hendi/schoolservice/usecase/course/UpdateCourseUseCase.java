package com.hendi.schoolservice.usecase.course;

import com.hendi.schoolservice.entity.course.exception.CourseNotFoundException;
import com.hendi.schoolservice.entity.course.gateway.CourseGateway;
import com.hendi.schoolservice.entity.course.model.CourseModel;
import com.hendi.schoolservice.entity.user.exception.UserNotFoundException;
import com.hendi.schoolservice.entity.user.gateway.UserGateway;
import com.hendi.schoolservice.entity.user.model.UserAccountModel;
import com.hendi.schoolservice.usecase.course.dto.ICourseUpdateData;

public class UpdateCourseUseCase {

    private final CourseGateway courseGateway;
    private final UserGateway userGateway;

    public UpdateCourseUseCase(
            CourseGateway courseGateway,
            UserGateway userGateway) {
        this.courseGateway = courseGateway;
        this.userGateway = userGateway;
    }

    public CourseModel execute(Long id, ICourseUpdateData data)
            throws UserNotFoundException, CourseNotFoundException {
        Long teacherId = data.teacherId();
        String name = data.name();
        String description = data.description();

        CourseModel courseData = courseGateway.findById(id).orElseThrow(CourseNotFoundException::new);
        courseData.setName(name);
        courseData.setDescription(description);

        UserAccountModel teacherData = userGateway.findById(teacherId).orElseThrow(UserNotFoundException::new);
        courseData.setTeacher(teacherData);

        return courseGateway.update(courseData);
    }

}
