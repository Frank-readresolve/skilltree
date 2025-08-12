package tech.readresolve.skilltree.dtos.in.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import tech.readresolve.skilltree.dtos.in.constraints.validators.TrainingDatesConsistentValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Constraint(validatedBy = TrainingDatesConsistentValidator.class)
public @interface TrainingDatesConsistent {

	String message() default "{skilltree.validation.constraints.TrainingDatesConsistent.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
