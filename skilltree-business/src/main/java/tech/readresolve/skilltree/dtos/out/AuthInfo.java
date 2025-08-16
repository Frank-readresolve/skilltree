package tech.readresolve.skilltree.dtos.out;

import tech.readresolve.skilltree.misc.ExcludeFromJacocoGeneratedReport;

public record AuthInfo(String token, AccountInfo accountInfo) {

    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
	return String.format("{token=[PROTECTED], accountInfo=%s}",
		accountInfo);
    }

}
