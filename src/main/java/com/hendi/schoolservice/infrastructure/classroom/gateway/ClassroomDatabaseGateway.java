package com.hendi.schoolservice.infrastructure.classroom.gateway;

import java.util.List;
import java.util.Optional;

import com.hendi.schoolservice.entity.classroom.gateway.ClassroomGateway;
import com.hendi.schoolservice.entity.classroom.model.ClassroomModel;
import com.hendi.schoolservice.infrastructure.config.db.repository.ClassroomRepository;
import com.hendi.schoolservice.infrastructure.config.db.schema.ClassroomSchema;

public class ClassroomDatabaseGateway implements ClassroomGateway {

    private final ClassroomRepository repository;

    public ClassroomDatabaseGateway(ClassroomRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClassroomModel create(ClassroomModel classroomModel) {
        return repository.save(new ClassroomSchema(classroomModel)).toClassroomModel();
    }

    @Override
    public ClassroomModel update(ClassroomModel classroomModel) {
        return repository.save(new ClassroomSchema(classroomModel)).toClassroomModel();
    }

    @Override
    public List<ClassroomModel> createAll(List<ClassroomModel> classroomModels) {
        return repository.saveAll(classroomModels.stream().map(ClassroomSchema::new).toList())
                .stream().map(ClassroomSchema::toClassroomModel).toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<ClassroomModel> findById(Long id) {
        return repository.findById(id).map(ClassroomSchema::toClassroomModel);
    }

    @Override
    public List<ClassroomModel> findAll() {
        return repository.findAll().stream().map(ClassroomSchema::toClassroomModel).toList();
    }

}
