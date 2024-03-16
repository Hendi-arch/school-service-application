package com.hendi.schoolservice.usecase.classroom;

import com.hendi.schoolservice.entity.classroom.gateway.ClassroomGateway;
import com.hendi.schoolservice.entity.classroom.model.ClassroomModel;
import com.hendi.schoolservice.entity.course.exception.CourseNotFoundException;
import com.hendi.schoolservice.entity.course.gateway.CourseGateway;
import com.hendi.schoolservice.entity.course.model.CourseModel;
import com.hendi.schoolservice.usecase.classroom.dto.IClassroomCreateData;

public class CreateClassroomUseCase {

    private final ClassroomGateway classroomGateway;
    private final CourseGateway courseGateway;

    public CreateClassroomUseCase(
            ClassroomGateway classroomGateway,
            CourseGateway courseGateway) {
        this.classroomGateway = classroomGateway;
        this.courseGateway = courseGateway;
    }

    public ClassroomModel execute(IClassroomCreateData data) throws CourseNotFoundException {
        Long courseId = data.courseId();
        String name = data.name();

        CourseModel courseData = courseGateway.findById(courseId).orElseThrow(CourseNotFoundException::new);
        ClassroomModel classroomData = new ClassroomModel(courseData, name);
        return classroomGateway.create(classroomData);
    }

}
