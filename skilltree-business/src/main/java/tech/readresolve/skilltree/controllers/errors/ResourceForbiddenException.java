package tech.readresolve.skilltree.controllers.errors;

public class ResourceForbiddenException extends RuntimeException {

    public ResourceForbiddenException(String message) {
	super(message);
    }

}
