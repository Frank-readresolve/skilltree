package tech.readresolve.skilltree;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Interpolator {

	private static final Pattern PATTERN = Pattern.compile("\\$\\{([^{}]*)\\}");

	private Interpolator() {
		// Not intantiable
	}

	public static String interpolate(String content) {
		if (null == content) {
			return null;
		}
		// var infers local variable type (!= null):
		var methods = methods(content);
		if (!methods.isEmpty()) {
			return replace(content, methods);
		}
		return content;
	}

	private static List<Candidate> methods(String content) {
		var matcher = PATTERN.matcher(content);
		var methods = new ArrayList<Candidate>();
		while (matcher.find()) {
			var candidate = new Candidate(matcher.group(1), matcher.start());
			methods.add(candidate);
		}
		return methods;
	}

	// Inner class
	private record Candidate(String token, int startIndex) {
		//
	}

	private static String replace(String content, List<Candidate> methods) {
		var result = content;
		for (int i = methods.size() - 1; i >= 0; i--) {
			var candidate = methods.get(i);
			var value = invoke(candidate);
			var placeHolder = String.format("${%s}", candidate.token);
			var next = candidate.startIndex + placeHolder.length();
			result = result.substring(0, candidate.startIndex) + value
					+ result.substring(next);
		}
		return result;
	}

	private static String invoke(Candidate candidate) {
		var token = candidate.token;
		var name = token.substring(0, token.indexOf('('));
		try {
			// Example of reflection, invocation of static method:
			var method = Functions.class.getDeclaredMethod(name);
			var result = method.invoke(null);
			return String.valueOf(result);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	// Inner static class:
	private static class Functions {

		// used only at runtime by reflection
		@SuppressWarnings("unused")
		static String year() {
			return Year.now().toString();
		}

		@SuppressWarnings("unused")
		static String yearPlusOne() {
			return Year.now().plusYears(1).toString();
		}

	}

}
