package com.hendi.schoolservice.entity.classroom.gateway;

import java.util.List;
import java.util.Optional;

import com.hendi.schoolservice.entity.classroom.model.ClassroomModel;

public interface ClassroomGateway {

    ClassroomModel create(ClassroomModel classroomModel);

    ClassroomModel update(ClassroomModel classroomModel);

    void delete(Long id);

    Optional<ClassroomModel> findById(Long id);

    List<ClassroomModel> findAll();

}
