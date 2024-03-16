package com.hendi.schoolservice.usecase.classroom;

import java.util.List;
import java.util.stream.Collectors;

import com.hendi.schoolservice.entity.classroom.gateway.ClassroomGateway;
import com.hendi.schoolservice.entity.classroom.model.ClassroomModel;
import com.hendi.schoolservice.entity.course.gateway.CourseGateway;
import com.hendi.schoolservice.entity.course.model.CourseModel;

public class ClassroomSeederUseCase {

    private final ClassroomGateway classroomGateway;
    private final CourseGateway courseGateway;

    public ClassroomSeederUseCase(ClassroomGateway classroomGateway, CourseGateway courseGateway) {
        this.classroomGateway = classroomGateway;
        this.courseGateway = courseGateway;
    }

    public void execute() {
        if (!classroomGateway.findAll().isEmpty()) {
            return;
        }

        List<CourseModel> courses = courseGateway.findAll();
        List<ClassroomModel> classroomsToSeed = courses.stream()
                .map(this::createClassroom)
                .collect(Collectors.toList());
        classroomGateway.createAll(classroomsToSeed);
    }

    private ClassroomModel createClassroom(CourseModel course) {
        return new ClassroomModel(course, String.valueOf(course.hashCode()));
    }

}
