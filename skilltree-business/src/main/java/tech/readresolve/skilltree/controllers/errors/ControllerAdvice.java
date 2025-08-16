package tech.readresolve.skilltree.controllers.errors;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import tech.readresolve.skilltree.misc.ExcludeFromJacocoGeneratedReport;

@RestControllerAdvice
final class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(
	    ResourceNotFoundException ex, WebRequest request) {
	return handleExceptionInternal(ex, null, new HttpHeaders(),
		HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentialsException(
	    BadCredentialsException ex, WebRequest request) {
	ValidationErrors validationErrors = new ValidationErrors();
	validationErrors.addGlobalError("E_BAD_CREDENTIALS");
	Errors<ValidationErrors> errors = Errors.of(validationErrors);
	return handleExceptionInternal(ex, errors, new HttpHeaders(),
		HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity<Object> handleDataAccessException(
	    DataAccessException ex, WebRequest request) {
	return handleExceptionInternal(ex, null, new HttpHeaders(),
		HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
	    MethodArgumentNotValidException ex, HttpHeaders headers,
	    HttpStatusCode status, WebRequest request) {
	ValidationErrors validationErrors = toValidationErrors(
		ex.getGlobalErrors(), ex.getFieldErrors());
	Errors<ValidationErrors> errors = Errors.of(validationErrors);
	return handleExceptionInternal(ex, errors, headers,
		HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    private static ValidationErrors toValidationErrors(
	    List<ObjectError> objectErrors, List<FieldError> fieldErrors) {
	ValidationErrors errors = new ValidationErrors();
	objectErrors.forEach(e -> errors.addGlobalError(e.getDefaultMessage()));
	fieldErrors.forEach(
		e -> errors.addFieldError(e.getField(), e.getDefaultMessage()));
	return errors;
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
	    Object body, HttpHeaders headers, HttpStatusCode status,
	    WebRequest request) {
	doLogDebug(ex);
	return super.handleExceptionInternal(ex, body, headers, status,
		request);
    }

    @ExcludeFromJacocoGeneratedReport
    private void doLogDebug(Exception ex) {
	if (logger.isDebugEnabled()) {
	    logger.debug(ex);
	}
    }

}
