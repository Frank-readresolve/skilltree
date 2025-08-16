package tech.readresolve.skilltree;

public final class Verifiers {

    private Verifiers() {
	// Not instantiable
    }

    public static boolean verifyToStringOverriden(Object obj) {
	var string = obj.toString();
	return string != null
		&& !string.startsWith(obj.getClass().getName() + "@");
    }

}
