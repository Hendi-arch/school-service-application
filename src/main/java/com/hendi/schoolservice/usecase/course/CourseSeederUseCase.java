package com.hendi.schoolservice.usecase.course;

import java.util.List;
import java.util.stream.Collectors;

import com.hendi.schoolservice.entity.course.gateway.CourseGateway;
import com.hendi.schoolservice.entity.course.model.CourseModel;
import com.hendi.schoolservice.entity.user.gateway.UserGateway;
import com.hendi.schoolservice.entity.user.model.UserAccountModel;
import com.hendi.schoolservice.infrastructure.config.db.schema.UserRoleSchema.RoleEnum;

public class CourseSeederUseCase {

    private final CourseGateway courseGateway;
    private final UserGateway userGateway;

    public CourseSeederUseCase(
            CourseGateway courseGateway,
            UserGateway userGateway) {
        this.courseGateway = courseGateway;
        this.userGateway = userGateway;
    }

    public void execute() {
        if (!courseGateway.findAll().isEmpty()) {
            return;
        }

        List<UserAccountModel> teachers = userGateway.findAll();
        List<CourseModel> coursesToSeed = teachers.stream()
                .filter(data -> data.getRole().getRole().equals(RoleEnum.TEACHER))
                .map(this::createCourse)
                .collect(Collectors.toList());
        courseGateway.createAll(coursesToSeed);
    }

    private CourseModel createCourse(UserAccountModel userAccount) {
        return new CourseModel(userAccount, "Test Course " + userAccount.getId(), "Test Course Description");
    }

}
