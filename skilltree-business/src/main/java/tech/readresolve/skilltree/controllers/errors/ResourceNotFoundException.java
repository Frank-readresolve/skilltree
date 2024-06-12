package tech.readresolve.skilltree.controllers.errors;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
	super(message);
    }

}
