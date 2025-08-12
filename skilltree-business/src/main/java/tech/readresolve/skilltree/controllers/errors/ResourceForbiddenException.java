package tech.readresolve.skilltree.controllers.errors;

@SuppressWarnings("serial")
public class ResourceForbiddenException extends RuntimeException {

	public ResourceForbiddenException(String message) {
		super(message);
	}

}
