package com.hendi.schoolservice.entity.grade.gateway;

import java.util.List;
import java.util.Optional;

import com.hendi.schoolservice.entity.grade.model.GradeModel;

public interface GradeGateway {

    GradeModel create(GradeModel gradeModel);

    GradeModel update(GradeModel gradeModel);

    void delete(Long id);

    Optional<GradeModel> findById(Long id);

    List<GradeModel> findAll();

}
