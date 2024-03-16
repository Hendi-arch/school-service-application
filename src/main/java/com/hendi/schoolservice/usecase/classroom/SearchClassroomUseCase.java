package com.hendi.schoolservice.usecase.classroom;

import java.util.List;

import com.hendi.schoolservice.entity.classroom.gateway.ClassroomGateway;
import com.hendi.schoolservice.entity.classroom.model.ClassroomModel;

public class SearchClassroomUseCase {

    private final ClassroomGateway classroomGateway;

    public SearchClassroomUseCase(ClassroomGateway classroomGateway) {
        this.classroomGateway = classroomGateway;
    }

    public List<ClassroomModel> execute() {
        return classroomGateway.findAll();
    }

}
