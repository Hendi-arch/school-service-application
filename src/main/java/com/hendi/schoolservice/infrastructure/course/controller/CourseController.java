package com.hendi.schoolservice.infrastructure.course.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.hendi.schoolservice.entity.course.model.CourseModel;
import com.hendi.schoolservice.entity.user.exception.UserNotFoundException;
import com.hendi.schoolservice.entity.userrole.exception.UserRoleNotFoundException;
import com.hendi.schoolservice.infrastructure.config.web.response.WebHttpResponse;
import com.hendi.schoolservice.infrastructure.course.dto.CourseCreateData;
import com.hendi.schoolservice.infrastructure.course.dto.CoursePublicData;
import com.hendi.schoolservice.infrastructure.course.dto.CourseUpdateData;
import com.hendi.schoolservice.usecase.course.CreateCourseUseCase;
import com.hendi.schoolservice.usecase.course.GetCourseUseCase;
import com.hendi.schoolservice.usecase.course.SearchCourseUseCase;
import com.hendi.schoolservice.usecase.course.UpdateCourseUseCase;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CreateCourseUseCase createCourseUseCase;
    private final GetCourseUseCase getCourseUseCase;
    private final UpdateCourseUseCase updateCourseUseCase;
    private final SearchCourseUseCase searchCourseUseCase;

    public CourseController(
            CreateCourseUseCase createCourseUseCase,
            GetCourseUseCase getCourseUseCase,
            UpdateCourseUseCase updateCourseUseCase,
            SearchCourseUseCase searchCourseUseCase) {
        this.createCourseUseCase = createCourseUseCase;
        this.getCourseUseCase = getCourseUseCase;
        this.updateCourseUseCase = updateCourseUseCase;
        this.searchCourseUseCase = searchCourseUseCase;
    }

    @PostMapping("/course")
    public ResponseEntity<WebHttpResponse<CoursePublicData>> createCourse(@Valid @RequestBody CourseCreateData request)
            throws UserNotFoundException {
        CourseModel courseData = createCourseUseCase.execute(request);
        return new ResponseEntity<>(WebHttpResponse.created(new CoursePublicData(courseData)), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<WebHttpResponse<List<CoursePublicData>>> getAllCourse() {
        List<CourseModel> courseDatas = searchCourseUseCase.execute();
        return new ResponseEntity<>(
                WebHttpResponse.ok(courseDatas.stream().map(CoursePublicData::new).toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebHttpResponse<CoursePublicData>> getCourseById(@PathVariable Long id)
            throws UserNotFoundException {
        CourseModel courseData = getCourseUseCase.execute(id);
        return new ResponseEntity<>(WebHttpResponse.ok(new CoursePublicData(courseData)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WebHttpResponse<CoursePublicData>> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseUpdateData request) throws UserNotFoundException, UserRoleNotFoundException {
        CourseModel courseData = updateCourseUseCase.execute(id, request);
        return new ResponseEntity<>(WebHttpResponse.ok(new CoursePublicData(courseData)), HttpStatus.OK);
    }

}
