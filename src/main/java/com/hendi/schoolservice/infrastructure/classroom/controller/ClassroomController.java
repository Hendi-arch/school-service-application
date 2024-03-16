package com.hendi.schoolservice.infrastructure.classroom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.hendi.schoolservice.entity.classroom.model.ClassroomModel;
import com.hendi.schoolservice.entity.user.exception.UserNotFoundException;
import com.hendi.schoolservice.entity.userrole.exception.UserRoleNotFoundException;
import com.hendi.schoolservice.infrastructure.classroom.dto.ClassroomCreateData;
import com.hendi.schoolservice.infrastructure.classroom.dto.ClassroomPublicData;
import com.hendi.schoolservice.infrastructure.classroom.dto.ClassroomUpdateData;
import com.hendi.schoolservice.infrastructure.config.web.response.WebHttpResponse;
import com.hendi.schoolservice.usecase.classroom.CreateClassroomUseCase;
import com.hendi.schoolservice.usecase.classroom.GetClassroomUseCase;
import com.hendi.schoolservice.usecase.classroom.SearchClassroomUseCase;
import com.hendi.schoolservice.usecase.classroom.UpdateClassroomUseCase;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {

    private final CreateClassroomUseCase createClassroomUseCase;
    private final GetClassroomUseCase getClassroomUseCase;
    private final UpdateClassroomUseCase updateClassroomUseCase;
    private final SearchClassroomUseCase searchClassroomUseCase;

    public ClassroomController(
            CreateClassroomUseCase createClassroomUseCase,
            GetClassroomUseCase getClassroomUseCase,
            UpdateClassroomUseCase updateClassroomUseCase,
            SearchClassroomUseCase searchClassroomUseCase) {
        this.createClassroomUseCase = createClassroomUseCase;
        this.getClassroomUseCase = getClassroomUseCase;
        this.updateClassroomUseCase = updateClassroomUseCase;
        this.searchClassroomUseCase = searchClassroomUseCase;
    }

    @PostMapping("/classroom")
    public ResponseEntity<WebHttpResponse<ClassroomPublicData>> createClassroom(
            @Valid @RequestBody ClassroomCreateData request)
            throws UserNotFoundException {
        ClassroomModel classroomData = createClassroomUseCase.execute(request);
        return new ResponseEntity<>(WebHttpResponse.created(new ClassroomPublicData(classroomData)),
                HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<WebHttpResponse<List<ClassroomPublicData>>> getAllClassrooms() {
        List<ClassroomModel> classroomDatas = searchClassroomUseCase.execute();
        return new ResponseEntity<>(
                WebHttpResponse.ok(classroomDatas.stream().map(ClassroomPublicData::new).toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebHttpResponse<ClassroomPublicData>> getClassroomById(@PathVariable Long id)
            throws UserNotFoundException {
        ClassroomModel classroomData = getClassroomUseCase.execute(id);
        return new ResponseEntity<>(WebHttpResponse.ok(new ClassroomPublicData(classroomData)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WebHttpResponse<ClassroomPublicData>> updateClassroom(
            @PathVariable Long id,
            @Valid @RequestBody ClassroomUpdateData request) throws UserNotFoundException, UserRoleNotFoundException {
        ClassroomModel classroomData = updateClassroomUseCase.execute(id, request);
        return new ResponseEntity<>(WebHttpResponse.ok(new ClassroomPublicData(classroomData)), HttpStatus.OK);
    }
}
