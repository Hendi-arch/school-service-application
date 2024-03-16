package com.hendi.schoolservice.infrastructure.config.web.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hendi.schoolservice.entity.classroom.gateway.ClassroomGateway;
import com.hendi.schoolservice.entity.course.gateway.CourseGateway;
import com.hendi.schoolservice.entity.user.gateway.UserGateway;
import com.hendi.schoolservice.entity.userrole.gateway.UserRoleGateway;
import com.hendi.schoolservice.entity.usertoken.gateway.UserTokenGateway;
import com.hendi.schoolservice.infrastructure.classroom.gateway.ClassroomDatabaseGateway;
import com.hendi.schoolservice.infrastructure.config.db.repository.ClassroomRepository;
import com.hendi.schoolservice.infrastructure.config.db.repository.CourseRepository;
import com.hendi.schoolservice.infrastructure.config.db.repository.UserRepository;
import com.hendi.schoolservice.infrastructure.config.db.repository.UserRoleRepository;
import com.hendi.schoolservice.infrastructure.config.db.repository.UserTokenRepository;
import com.hendi.schoolservice.infrastructure.config.web.security.service.MyUserDetailService;
import com.hendi.schoolservice.infrastructure.config.web.security.util.JwtUtils;
import com.hendi.schoolservice.infrastructure.course.gateway.CourseDatabaseGateway;
import com.hendi.schoolservice.infrastructure.user.gateway.UserDatabaseGateway;
import com.hendi.schoolservice.infrastructure.userrole.gateway.UserRoleDatabaseGateway;
import com.hendi.schoolservice.infrastructure.usertoken.gateway.UserTokenDatabaseGateway;
import com.hendi.schoolservice.usecase.classroom.ClassroomSeederUseCase;
import com.hendi.schoolservice.usecase.classroom.CreateClassroomUseCase;
import com.hendi.schoolservice.usecase.classroom.GetClassroomUseCase;
import com.hendi.schoolservice.usecase.classroom.SearchClassroomUseCase;
import com.hendi.schoolservice.usecase.classroom.UpdateClassroomUseCase;
import com.hendi.schoolservice.usecase.course.CourseSeederUseCase;
import com.hendi.schoolservice.usecase.course.CreateCourseUseCase;
import com.hendi.schoolservice.usecase.course.GetCourseUseCase;
import com.hendi.schoolservice.usecase.course.SearchCourseUseCase;
import com.hendi.schoolservice.usecase.course.UpdateCourseUseCase;
import com.hendi.schoolservice.usecase.user.CreateUserUseCase;
import com.hendi.schoolservice.usecase.user.GetUserUseCase;
import com.hendi.schoolservice.usecase.user.LoginUserUseCase;
import com.hendi.schoolservice.usecase.user.SearchUserUseCase;
import com.hendi.schoolservice.usecase.user.UpdateUserUseCase;
import com.hendi.schoolservice.usecase.user.UserSeederUseCase;
import com.hendi.schoolservice.usecase.userrole.GetUserRoleUseCase;
import com.hendi.schoolservice.usecase.userrole.SearchUserRoleUseCase;
import com.hendi.schoolservice.usecase.userrole.SeederUserRolesUseCase;
import com.hendi.schoolservice.usecase.usertoken.DeleteUserTokenUseCase;
import com.hendi.schoolservice.usecase.usertoken.GetUserTokenUseCase;

@Configuration
public class MvcConfiguration {

    @Bean
    public CreateUserUseCase createUserUseCase(
            UserRepository userRepository,
            UserRoleRepository userRoleRepository,
            PasswordEncoder passwordEncoder) {
        UserGateway userGateway = new UserDatabaseGateway(userRepository);
        UserRoleGateway userRoleGateway = new UserRoleDatabaseGateway(userRoleRepository);
        return new CreateUserUseCase(userGateway, userRoleGateway, passwordEncoder);
    }

    @Bean
    public GetUserUseCase getUserUseCase(UserRepository userRepository) {
        UserGateway userGateway = new UserDatabaseGateway(userRepository);
        return new GetUserUseCase(userGateway);
    }

    @Bean
    public LoginUserUseCase loginUserUseCase(
            UserRepository userRepository,
            UserTokenRepository userTokenRepository,
            JwtUtils jwtUtils,
            MyUserDetailService myUserDetailService,
            PasswordEncoder passwordEncoder) {
        UserGateway userGateway = new UserDatabaseGateway(userRepository);
        UserTokenGateway userTokenGateway = new UserTokenDatabaseGateway(userTokenRepository);

        return new LoginUserUseCase(
                userGateway,
                userTokenGateway,
                jwtUtils,
                myUserDetailService,
                passwordEncoder);
    }

    @Bean
    public SearchUserUseCase searchUserUseCase(UserRepository userRepository) {
        UserGateway userGateway = new UserDatabaseGateway(userRepository);
        return new SearchUserUseCase(userGateway);
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase(
            UserRepository userRepository,
            UserRoleRepository userRoleRepository,
            PasswordEncoder passwordEncoder) {
        UserGateway userGateway = new UserDatabaseGateway(userRepository);
        UserRoleGateway userRoleGateway = new UserRoleDatabaseGateway(userRoleRepository);
        return new UpdateUserUseCase(userGateway, userRoleGateway, passwordEncoder);
    }

    @Bean
    public UserSeederUseCase userSeederUseCase(
            UserRepository userRepository,
            UserRoleRepository userRoleRepository,
            PasswordEncoder passwordEncoder) {
        UserGateway userGateway = new UserDatabaseGateway(userRepository);
        UserRoleGateway userRoleGateway = new UserRoleDatabaseGateway(userRoleRepository);
        return new UserSeederUseCase(userGateway, userRoleGateway, passwordEncoder);
    }

    @Bean
    public GetUserTokenUseCase getUserTokenUseCase(UserTokenRepository userTokenRepository) {
        UserTokenGateway userTokenGateway = new UserTokenDatabaseGateway(userTokenRepository);
        return new GetUserTokenUseCase(userTokenGateway);
    }

    @Bean
    public DeleteUserTokenUseCase deleteUserTokenUseCase(UserTokenRepository userTokenRepository) {
        UserTokenGateway userTokenGateway = new UserTokenDatabaseGateway(userTokenRepository);
        return new DeleteUserTokenUseCase(userTokenGateway);
    }

    @Bean
    public GetUserRoleUseCase getUserRoleUseCase(UserRoleRepository userRoleRepository) {
        UserRoleGateway userRoleGateway = new UserRoleDatabaseGateway(userRoleRepository);
        return new GetUserRoleUseCase(userRoleGateway);
    }

    @Bean
    public SearchUserRoleUseCase searchUserRoleUseCase(UserRoleRepository userRoleRepository) {
        UserRoleGateway userRoleGateway = new UserRoleDatabaseGateway(userRoleRepository);
        return new SearchUserRoleUseCase(userRoleGateway);
    }

    @Bean
    public SeederUserRolesUseCase seederUserRolesUseCase(UserRoleRepository userRoleRepository) {
        UserRoleGateway userRoleGateway = new UserRoleDatabaseGateway(userRoleRepository);
        return new SeederUserRolesUseCase(userRoleGateway);
    }

    @Bean
    public CourseSeederUseCase courseSeederUseCase(CourseRepository courseRepository, UserRepository userRepository) {
        CourseGateway courseGateway = new CourseDatabaseGateway(courseRepository);
        UserGateway userGateway = new UserDatabaseGateway(userRepository);
        return new CourseSeederUseCase(courseGateway, userGateway);
    }

    @Bean
    public CreateCourseUseCase createCourseUseCase(
            CourseRepository courseRepository,
            UserRepository userRepository) {
        CourseGateway courseGateway = new CourseDatabaseGateway(courseRepository);
        UserGateway userGateway = new UserDatabaseGateway(userRepository);
        return new CreateCourseUseCase(courseGateway, userGateway);
    }

    @Bean
    public GetCourseUseCase getCourseUseCase(CourseRepository courseRepository) {
        CourseGateway courseGateway = new CourseDatabaseGateway(courseRepository);
        return new GetCourseUseCase(courseGateway);
    }

    @Bean
    public SearchCourseUseCase searchCourseUseCase(CourseRepository courseRepository) {
        CourseGateway courseGateway = new CourseDatabaseGateway(courseRepository);
        return new SearchCourseUseCase(courseGateway);
    }

    @Bean
    public UpdateCourseUseCase updateCourseUseCase(
            CourseRepository courseRepository,
            UserRepository userRepository) {
        CourseGateway courseGateway = new CourseDatabaseGateway(courseRepository);
        UserGateway userGateway = new UserDatabaseGateway(userRepository);
        return new UpdateCourseUseCase(courseGateway, userGateway);
    }

    @Bean
    public ClassroomSeederUseCase classroomSeederUseCase(
            ClassroomRepository classroomRepository,
            CourseRepository courseRepository) {
        ClassroomGateway classroomGateway = new ClassroomDatabaseGateway(classroomRepository);
        CourseGateway courseGateway = new CourseDatabaseGateway(courseRepository);
        return new ClassroomSeederUseCase(classroomGateway, courseGateway);
    }

    @Bean
    public CreateClassroomUseCase createClassroomUseCase(
            ClassroomRepository classroomRepository,
            CourseRepository courseRepository) {
        ClassroomGateway classroomGateway = new ClassroomDatabaseGateway(classroomRepository);
        CourseGateway courseGateway = new CourseDatabaseGateway(courseRepository);
        return new CreateClassroomUseCase(classroomGateway, courseGateway);
    }

    @Bean
    public GetClassroomUseCase getClassroomUseCase(ClassroomRepository classroomRepository) {
        ClassroomGateway classroomGateway = new ClassroomDatabaseGateway(classroomRepository);
        return new GetClassroomUseCase(classroomGateway);
    }

    @Bean
    public SearchClassroomUseCase searchClassroomUseCase(ClassroomRepository classroomRepository) {
        ClassroomGateway classroomGateway = new ClassroomDatabaseGateway(classroomRepository);
        return new SearchClassroomUseCase(classroomGateway);
    }

    @Bean
    public UpdateClassroomUseCase updateClassroomUseCase(
            ClassroomRepository classroomRepository,
            CourseRepository courseRepository) {
        ClassroomGateway classroomGateway = new ClassroomDatabaseGateway(classroomRepository);
        CourseGateway courseGateway = new CourseDatabaseGateway(courseRepository);
        return new UpdateClassroomUseCase(classroomGateway, courseGateway);
    }

}
