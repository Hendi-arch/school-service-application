package com.hendi.schoolservice;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.hendi.schoolservice.infrastructure.config.db.schema.UserRoleSchema.RoleEnum;
import com.hendi.schoolservice.infrastructure.config.web.response.WebHttpResponse;
import com.hendi.schoolservice.infrastructure.user.dto.UserLoginData;
import com.hendi.schoolservice.infrastructure.user.dto.UserPublicData;
import com.hendi.schoolservice.infrastructure.user.dto.UserUpdateData;
import com.hendi.schoolservice.infrastructure.userrole.dto.UserRolePublicData;
import com.hendi.schoolservice.infrastructure.usertoken.dto.UserTokenPublicData;
import com.hendi.schoolservice.shared.TestUtils;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "spring.profiles.active=dev" })
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerAcceptanceTest {

        @Container
        @ServiceConnection
        private static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

        @DynamicPropertySource
        private static void setProperties(DynamicPropertyRegistry registry) {
                registry.add("spring.datasource.url", postgres::getJdbcUrl);
                registry.add("spring.datasource.username", postgres::getUsername);
                registry.add("spring.datasource.password", postgres::getPassword);
        }

        @Autowired
        private TestRestTemplate restTemplate;

        @LocalServerPort
        private int serverPort;

        private static final String SUPERADMIN = "super_admin";
        private static final String USER = "user";
        private static final String PASSWORD = "SecurePassword123$";
        private static Long superAdminUserId;
        private static String superAdminJwtToken;
        private static LocalDateTime expiryDateTime;

        @Test
        @Order(1)
        void testLoginSuperAdminSuccess() throws JsonProcessingException {
                UserLoginData loginData = new UserLoginData(SUPERADMIN, PASSWORD);
                ResponseEntity<WebHttpResponse<UserPublicData>> responseEntity = restTemplate.exchange(
                                TestUtils.getUsersBaseUrl(serverPort, "/login"), HttpMethod.POST,
                                new HttpEntity<>(loginData),
                                TestUtils.webHttpResponseType());

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertNotNull(responseEntity.getBody());

                WebHttpResponse<UserPublicData> responseBody = TestUtils.deserializeResponseBody(
                                responseEntity.getBody(),
                                new TypeReference<WebHttpResponse<UserPublicData>>() {
                                });
                assertNotNull(responseBody.getData());

                UserPublicData publicData = responseBody.getData();
                assertNotNull(publicData.id());
                superAdminUserId = publicData.id();

                assertEquals(SUPERADMIN, publicData.username());
                assertNotNull(publicData.createdAt());
                assertNotNull(publicData.updatedAt());

                assertNotNull(publicData.jwtExpiryDateTime());
                expiryDateTime = publicData.jwtExpiryDateTime();

                assertNotNull(publicData.jwtToken());
                superAdminJwtToken = publicData.jwtToken();

                assertNotNull(publicData.role());
                assertEquals(RoleEnum.SUPER_ADMIN.name(), publicData.role().role());
        }

        @Test
        @Order(2)
        void testGetAllUsers() throws JsonProcessingException {
                HttpHeaders httpHeaders = TestUtils.getAuthHeaders(superAdminJwtToken);
                ResponseEntity<WebHttpResponse<List<UserPublicData>>> responseEntity = restTemplate.exchange(
                                TestUtils.getUsersBaseUrl(serverPort, "/all"), HttpMethod.GET,
                                new HttpEntity<>(httpHeaders),
                                TestUtils.webHttpResponseType());

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertNotNull(responseEntity.getBody());

                WebHttpResponse<List<UserPublicData>> responseBody = TestUtils.deserializeResponseBody(
                                responseEntity.getBody(),
                                new TypeReference<WebHttpResponse<List<UserPublicData>>>() {
                                });
                assertNotNull(responseBody.getData());
                assertFalse(responseBody.getData().isEmpty());
        }

        @Test
        @Order(3)
        void testGetUserById() throws JsonProcessingException {
                HttpHeaders httpHeaders = TestUtils.getAuthHeaders(superAdminJwtToken);
                ResponseEntity<WebHttpResponse<UserPublicData>> responseEntity = restTemplate.exchange(
                                TestUtils.getUsersBaseUrl(serverPort, "/{id}"), HttpMethod.GET,
                                new HttpEntity<>(httpHeaders),
                                TestUtils.webHttpResponseType(),
                                superAdminUserId);

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertNotNull(responseEntity.getBody());

                WebHttpResponse<UserPublicData> responseBody = TestUtils.deserializeResponseBody(
                                responseEntity.getBody(),
                                new TypeReference<WebHttpResponse<UserPublicData>>() {
                                });
                assertNotNull(responseBody.getData());

                UserPublicData publicData = responseBody.getData();
                assertEquals(superAdminUserId, publicData.id());
                assertEquals(SUPERADMIN, publicData.username());

                assertNotNull(publicData.role());
                assertEquals(RoleEnum.SUPER_ADMIN.name(), publicData.role().role());
        }

        @Test
        @Order(4)
        void testUpdateUser() throws JsonProcessingException {
                UserUpdateData updateData = new UserUpdateData(PASSWORD, 1L);
                HttpHeaders httpHeaders = TestUtils.getAuthHeaders(superAdminJwtToken);
                ResponseEntity<WebHttpResponse<UserPublicData>> responseEntity = restTemplate.exchange(
                                TestUtils.getUsersBaseUrl(serverPort, "/{id}"), HttpMethod.PUT,
                                new HttpEntity<>(updateData, httpHeaders),
                                TestUtils.webHttpResponseType(), superAdminUserId);

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertNotNull(responseEntity.getBody());

                WebHttpResponse<UserPublicData> responseBody = TestUtils.deserializeResponseBody(
                                responseEntity.getBody(),
                                new TypeReference<WebHttpResponse<UserPublicData>>() {
                                });
                assertNotNull(responseBody.getData());

                UserPublicData publicData = responseBody.getData();
                assertEquals(superAdminUserId, publicData.id());
                assertEquals(SUPERADMIN, publicData.username());
                assertNotNull(publicData.createdAt());
                assertNotNull(publicData.updatedAt());

                assertNotNull(publicData.role());
                assertEquals(RoleEnum.SUPER_ADMIN.name(), publicData.role().role());
        }

        @Test
        @Order(5)
        void testGetAllUserRole() throws JsonProcessingException {
                HttpHeaders httpHeaders = TestUtils.getAuthHeaders(superAdminJwtToken);
                ResponseEntity<WebHttpResponse<List<UserRolePublicData>>> responseEntity = restTemplate.exchange(
                                TestUtils.getUserRolesBaseUrl(serverPort, "/all"), HttpMethod.GET,
                                new HttpEntity<>(httpHeaders),
                                TestUtils.webHttpResponseType());

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertNotNull(responseEntity.getBody());

                WebHttpResponse<List<UserRolePublicData>> responseBody = TestUtils.deserializeResponseBody(
                                responseEntity.getBody(),
                                new TypeReference<WebHttpResponse<List<UserRolePublicData>>>() {
                                });
                assertNotNull(responseBody.getData());
                assertFalse(responseBody.getData().isEmpty());
        }

        @Test
        @Order(6)
        void testGetUserRoleById() throws JsonProcessingException {
                HttpHeaders httpHeaders = TestUtils.getAuthHeaders(superAdminJwtToken);
                ResponseEntity<WebHttpResponse<UserRolePublicData>> responseEntity = restTemplate.exchange(
                                TestUtils.getUserRolesBaseUrl(serverPort, "/{id}"), HttpMethod.GET,
                                new HttpEntity<>(httpHeaders),
                                TestUtils.webHttpResponseType(),
                                superAdminUserId);

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertNotNull(responseEntity.getBody());

                WebHttpResponse<UserRolePublicData> responseBody = TestUtils.deserializeResponseBody(
                                responseEntity.getBody(),
                                new TypeReference<WebHttpResponse<UserRolePublicData>>() {
                                });
                assertNotNull(responseBody.getData());

                UserRolePublicData publicData = responseBody.getData();
                assertEquals(1L, publicData.id());
                assertEquals(RoleEnum.SUPER_ADMIN.name(), publicData.role());
                assertNotNull(publicData.createdAt());
                assertNotNull(publicData.updatedAt());
        }

        @Test
        @Order(7)
        void testGetUserTokenByToken() throws JsonProcessingException {
                HttpHeaders httpHeaders = TestUtils.getAuthHeaders(superAdminJwtToken);
                ResponseEntity<WebHttpResponse<UserTokenPublicData>> responseEntity = restTemplate.exchange(
                                TestUtils.getUserTokensBaseUrl(serverPort, "/{authToken}"), HttpMethod.GET,
                                new HttpEntity<>(httpHeaders),
                                TestUtils.webHttpResponseType(),
                                superAdminJwtToken);

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertNotNull(responseEntity.getBody());

                WebHttpResponse<UserTokenPublicData> responseBody = TestUtils.deserializeResponseBody(
                                responseEntity.getBody(),
                                new TypeReference<WebHttpResponse<UserTokenPublicData>>() {
                                });
                assertNotNull(responseBody.getData());

                UserTokenPublicData publicData = responseBody.getData();
                assertEquals(superAdminJwtToken, publicData.token());
                assertEquals(expiryDateTime, publicData.expiryDateTime());
        }

        @Test
        @Order(8)
        void testDeleteUserTokenByToken() throws JsonProcessingException {
                HttpHeaders httpHeaders = TestUtils.getAuthHeaders(superAdminJwtToken);
                ResponseEntity<WebHttpResponse<String>> responseEntity = restTemplate.exchange(
                                TestUtils.getUserTokensBaseUrl(serverPort, "/{authToken}"), HttpMethod.DELETE,
                                new HttpEntity<>(httpHeaders),
                                TestUtils.webHttpResponseType(),
                                superAdminJwtToken);

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertNotNull(responseEntity.getBody());
        }

        @Test
        @Order(9)
        void testLoginUserSuccess() throws JsonProcessingException {
                UserLoginData loginData = new UserLoginData(USER, PASSWORD);
                ResponseEntity<WebHttpResponse<UserPublicData>> responseEntity = restTemplate.exchange(
                                TestUtils.getUsersBaseUrl(serverPort, "/login"), HttpMethod.POST,
                                new HttpEntity<>(loginData),
                                TestUtils.webHttpResponseType());

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertNotNull(responseEntity.getBody());

                WebHttpResponse<UserPublicData> responseBody = TestUtils.deserializeResponseBody(
                                responseEntity.getBody(),
                                new TypeReference<WebHttpResponse<UserPublicData>>() {
                                });
                assertNotNull(responseBody.getData());

                UserPublicData publicData = responseBody.getData();
                assertNotNull(publicData.id());

                assertEquals(USER, publicData.username());
                assertNotNull(publicData.createdAt());
                assertNotNull(publicData.updatedAt());
                assertNotNull(publicData.jwtExpiryDateTime());
                assertNotNull(publicData.jwtToken());

                assertNotNull(publicData.role());
                assertEquals(RoleEnum.USER.name(), publicData.role().role());
        }

}
