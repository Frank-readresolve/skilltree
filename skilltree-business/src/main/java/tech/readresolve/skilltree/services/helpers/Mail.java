package tech.readresolve.skilltree.services.helpers;

record Mail(String to, String subject, String content) {

	@Override
	public String toString() {
		// Content may contain secret data
		return String.format("{to=%s, subject=%s, content=[PROTECTED]}", to,
				subject);
	}

}
