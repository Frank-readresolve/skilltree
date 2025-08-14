package tech.readresolve.skilltree.services.helpers;

record Mail(String to, String subject, String content) {

    @Override
    public String toString() {
	// DO NOT OUTPUT content!
	return String.format("{to=%s, subject=%s, [PROTECTED]}", to, subject);
    }

}
