package tech.readresolve.skilltree.dtos.out;

public record AuthInfo(String token, AccountInfo accountInfo) {

    @Override
    public String toString() {
	return String.format("{token=[PROTECTED], accountInfo=%s}",
		accountInfo);
    }

}
