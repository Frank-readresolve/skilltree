package tech.readresolve.skilltree.services.helpers;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public final class PasswordUtil {

	private PasswordUtil() {
		// Not instantiable
	}

	public static String randomPassword() {
		Random random = new SecureRandom();
		String lowers = RandomStringUtils.random(3, 97, 123, false, false, null,
				random);
		String uppers = RandomStringUtils.random(3, 65, 91, false, false, null,
				random);
		String digits = RandomStringUtils.random(3, 48, 58, false, false, null,
				random);
		String puncts = RandomStringUtils.random(3, 33, 48, false, false, null,
				random);
		String straight = lowers + uppers + digits + puncts;
		List<String> chars = Arrays.asList(straight.split(""));
		Collections.shuffle(chars, random);
		return StringUtils.join(chars, "");
	}

}
