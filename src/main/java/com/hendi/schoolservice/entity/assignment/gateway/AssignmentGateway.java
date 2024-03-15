package com.hendi.schoolservice.entity.assignment.gateway;

import java.util.List;
import java.util.Optional;

import com.hendi.schoolservice.entity.assignment.model.AssignmentModel;
import com.hendi.schoolservice.entity.classroom.model.ClassroomModel;

public interface AssignmentGateway {

    AssignmentModel create(ClassroomModel classroomModel);

    AssignmentModel update(ClassroomModel classroomModel);

    void delete(Long id);

    Optional<AssignmentModel> findById(Long id);

    List<AssignmentModel> findAll();

}
