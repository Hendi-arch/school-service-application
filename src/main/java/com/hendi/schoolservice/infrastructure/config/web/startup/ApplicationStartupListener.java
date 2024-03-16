package com.hendi.schoolservice.infrastructure.config.web.startup;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.hendi.schoolservice.usecase.classroom.ClassroomSeederUseCase;
import com.hendi.schoolservice.usecase.course.CourseSeederUseCase;
import com.hendi.schoolservice.usecase.user.UserSeederUseCase;
import com.hendi.schoolservice.usecase.userrole.SeederUserRolesUseCase;

@Component
public class ApplicationStartupListener {

    private final SeederUserRolesUseCase seederUserRolesUseCase;
    private final UserSeederUseCase userSeederUseCase;
    private final CourseSeederUseCase courseSeederUseCase;
    private final ClassroomSeederUseCase classroomSeederUseCase;

    public ApplicationStartupListener(
            SeederUserRolesUseCase seederUserRolesUseCase,
            UserSeederUseCase userSeederUseCase,
            CourseSeederUseCase courseSeederUseCase,
            ClassroomSeederUseCase classroomSeederUseCase) {
        this.seederUserRolesUseCase = seederUserRolesUseCase;
        this.userSeederUseCase = userSeederUseCase;
        this.courseSeederUseCase = courseSeederUseCase;
        this.classroomSeederUseCase = classroomSeederUseCase;
    }

    @EventListener(classes = ApplicationReadyEvent.class)
    public void handleApplicationStartup(ApplicationReadyEvent event) {
        seederUserRolesUseCase.execute();
        userSeederUseCase.execute();
        courseSeederUseCase.execute();
        classroomSeederUseCase.execute();
    }

}
