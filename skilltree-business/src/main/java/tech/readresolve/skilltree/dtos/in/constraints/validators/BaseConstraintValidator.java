package tech.readresolve.skilltree.dtos.in.constraints.validators;

import java.lang.annotation.Annotation;
import java.util.Map;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import tech.readresolve.skilltree.misc.ExcludeFromJacocoGeneratedReport;

abstract class BaseConstraintValidator<A extends Annotation, T>
	implements ConstraintValidator<A, T> {

    BaseConstraintValidator() {
	//
    }

    @SuppressWarnings("unchecked")
    @ExcludeFromJacocoGeneratedReport
    protected static String pathVariableString(String name) {
	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
		.currentRequestAttributes()).getRequest();
	Map<String, String> pathVariables = (Map<String, String>) request
		.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
	return pathVariables.get(name);
    }

    @ExcludeFromJacocoGeneratedReport
    protected static Long pathVariableAsLong(String name) {
	return Long.valueOf(pathVariableString(name));
    }

    @ExcludeFromJacocoGeneratedReport
    protected static Integer pathVariableAsInteger(String name) {
	return Integer.valueOf(pathVariableString(name));
    }

    @ExcludeFromJacocoGeneratedReport
    protected static Boolean pathVariableAsBoolean(String name) {
	return Boolean.valueOf(pathVariableString(name));
    }

}
