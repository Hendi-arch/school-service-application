package com.hendi.schoolservice.infrastructure.config.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hendi.schoolservice.entity.user.exception.PasswordNotMatchException;
import com.hendi.schoolservice.entity.user.exception.UserNotFoundException;
import com.hendi.schoolservice.entity.userrole.exception.UserRoleNotFoundException;
import com.hendi.schoolservice.entity.usertoken.exception.UserTokenNotFoundException;
import com.hendi.schoolservice.entity.usertoken.exception.UserTokenRevokedException;
import com.hendi.schoolservice.infrastructure.config.web.response.WebHttpErrorResponse;
import com.hendi.schoolservice.infrastructure.config.web.response.WebHttpResponse;

import jakarta.persistence.PersistenceException;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

	/**
	 * Handles validation errors and converts them into a standardized format.
	 *
	 * @param ex The ConstraintViolationException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(BindException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handleValidationError(BindException ex) {

		// Convert ConstraintViolation objects into WebHttpErrorResponse objects
		List<WebHttpErrorResponse> messages = ex.getFieldErrors().stream()
				.map(violation -> new WebHttpErrorResponse(
						violation.getField(),
						violation.getDefaultMessage()))
				.collect(Collectors.toList());

		return ResponseEntity.badRequest().body(WebHttpResponse.badRequest(messages));
	}

	/**
	 * Handles when a user is not found.
	 *
	 * @param ex The UserNotFoundException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handleUserNotFoundException(
			UserNotFoundException ex) {
		List<WebHttpErrorResponse> messages = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(WebHttpResponse.notFound(messages));
	}

	/**
	 * Handles when a password does not match.
	 *
	 * @param ex The PasswordNotMatchException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(PasswordNotMatchException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handlePasswordNotMatchException(
			PasswordNotMatchException ex) {
		List<WebHttpErrorResponse> messages = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(WebHttpResponse.badRequest(messages));
	}

	/**
	 * Handles when a user token is not found.
	 *
	 * @param ex The UserTokenNotFoundException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(UserTokenNotFoundException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handleUserTokenNotFoundException(
			UserTokenNotFoundException ex) {
		List<WebHttpErrorResponse> messages = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(WebHttpResponse.notFound(messages));
	}

	/**
	 * Handles generic exceptions and converts them into a standardized format.
	 *
	 * @param ex The Exception instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handleGenericException(Exception ex) {
		List<WebHttpErrorResponse> messages = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(WebHttpResponse.internalServerError(messages));
	}

	/**
	 * Handles JPA system exceptions and converts them into a standardized format.
	 *
	 * @param ex The JpaSystemException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(PersistenceException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handlePersistenceException(
			PersistenceException ex) {
		List<WebHttpErrorResponse> messages = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.internalServerError().body(WebHttpResponse.internalServerError(messages));
	}

	/**
	 * Handles data integrity violation exceptions and converts them into a
	 * standardized format.
	 *
	 * @param ex The DataIntegrityViolationException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handleDataIntegrityViolationException(
			DataIntegrityViolationException ex) {
		List<WebHttpErrorResponse> messages = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.internalServerError().body(WebHttpResponse.internalServerError(messages));
	}

	/**
	 * Handles invalid data access API usage exceptions and converts them into a
	 * standardized format.
	 *
	 * @param ex The InvalidDataAccessApiUsageException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handleInvalidDataAccessApiUsageException(
			InvalidDataAccessApiUsageException ex) {
		List<WebHttpErrorResponse> message = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.internalServerError().body(WebHttpResponse.internalServerError(message));
	}

	/**
	 * Handles when authentication fails, usually due to revoked credentials.
	 *
	 * @param ex The UserTokenRevokedException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(UserTokenRevokedException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handleUserTokenRevokedException(
			UserTokenRevokedException ex) {
		List<WebHttpErrorResponse> message = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(WebHttpResponse.unauthorized(message));
	}

	/**
	 * Handles when a user role is not found.
	 *
	 * @param ex The UserRoleNotFoundException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(UserRoleNotFoundException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handleUserRoleNotFoundException(
			UserRoleNotFoundException ex) {
		List<WebHttpErrorResponse> messages = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(WebHttpResponse.notFound(messages));
	}

}
