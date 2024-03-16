package com.hendi.schoolservice.usecase.classroom;

import com.hendi.schoolservice.entity.classroom.exception.ClassroomNotFoundException;
import com.hendi.schoolservice.entity.classroom.gateway.ClassroomGateway;
import com.hendi.schoolservice.entity.classroom.model.ClassroomModel;

public class GetClassroomUseCase {

    private final ClassroomGateway classroomGateway;

    public GetClassroomUseCase(ClassroomGateway classroomGateway) {
        this.classroomGateway = classroomGateway;
    }

    public ClassroomModel execute(Long id) throws ClassroomNotFoundException {
        return classroomGateway
                .findById(id)
                .orElseThrow(ClassroomNotFoundException::new);
    }

}
