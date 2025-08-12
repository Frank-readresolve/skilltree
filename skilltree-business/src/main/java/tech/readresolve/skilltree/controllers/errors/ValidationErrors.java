package tech.readresolve.skilltree.controllers.errors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

final class ValidationErrors {

	private final Collection<ValidationError> globals;

	private final Map<String, Collection<ValidationError>> fields;

	ValidationErrors() {
		globals = new ArrayList<>();
		fields = new HashMap<>();
	}

	void addGlobalError(String code) {
		globals.add(new ValidationError(code));
	}

	void addFieldError(String name, String code) {
		Collection<ValidationError> errors = init(name);
		errors.add(new ValidationError(code));
	}

	private Collection<ValidationError> init(String name) {
		return fields.computeIfAbsent(name,
				k -> new ArrayList<ValidationError>());
	}

	public Collection<ValidationError> getGlobals() {
		return Collections.unmodifiableCollection(globals);
	}

	public Map<String, Collection<ValidationError>> getFields() {
		return Collections.unmodifiableMap(fields);
	}

	@Override
	public String toString() {
		return String.format("{globals=%s, fields=%s}", globals, fields);
	}

	private record ValidationError(String code) {
		//
	}

}
