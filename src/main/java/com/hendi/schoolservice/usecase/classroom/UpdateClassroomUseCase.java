package com.hendi.schoolservice.usecase.classroom;

import com.hendi.schoolservice.entity.classroom.exception.ClassroomNotFoundException;
import com.hendi.schoolservice.entity.classroom.gateway.ClassroomGateway;
import com.hendi.schoolservice.entity.classroom.model.ClassroomModel;
import com.hendi.schoolservice.entity.course.exception.CourseNotFoundException;
import com.hendi.schoolservice.entity.course.gateway.CourseGateway;
import com.hendi.schoolservice.entity.course.model.CourseModel;
import com.hendi.schoolservice.usecase.classroom.dto.IClassroomUpdateData;

public class UpdateClassroomUseCase {

    private final ClassroomGateway classroomGateway;
    private final CourseGateway courseGateway;

    public UpdateClassroomUseCase(
            ClassroomGateway classroomGateway,
            CourseGateway courseGateway) {
        this.classroomGateway = classroomGateway;
        this.courseGateway = courseGateway;
    }

    public ClassroomModel execute(Long id, IClassroomUpdateData data)
            throws ClassroomNotFoundException, CourseNotFoundException {
        Long courseId = data.courseId();
        String name = data.name();

        ClassroomModel classroomData = classroomGateway.findById(id).orElseThrow(ClassroomNotFoundException::new);
        classroomData.setName(name);

        CourseModel courseData = courseGateway.findById(courseId).orElseThrow(CourseNotFoundException::new);
        classroomData.setCourse(courseData);

        return classroomGateway.update(classroomData);
    }

}
