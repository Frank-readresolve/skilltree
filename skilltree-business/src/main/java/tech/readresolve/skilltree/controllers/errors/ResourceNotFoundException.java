package tech.readresolve.skilltree.controllers.errors;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
	super(message);
    }

}
