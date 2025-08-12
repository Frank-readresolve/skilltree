package tech.readresolve.skilltree.controllers.errors;

record Errors<T>(T errors) {

	static <T> Errors<T> of(T errors) {
		return new Errors<>(errors);
	}

}
