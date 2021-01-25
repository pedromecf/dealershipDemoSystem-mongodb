package br.com.dlcars.model.util;

public interface CpfValidation {

	default boolean validateCpf(String cpf) {
		if (cpf.length() != 11) {
			return false;
		}
		String testCpf = cpf.substring(0, 9);
		String firstDigit;
		String secondDigit;
		int sum = 0;
		int decrement = 10;

		for (int i = 0; i < testCpf.length(); i++) {
			sum += ((int) testCpf.charAt(i) - 48) * decrement;
			decrement--;
		}
		firstDigit = Integer.toString(11 - (sum % 11));
		firstDigit = firstDigit.equals("11") || firstDigit.equals("10") ? "0" : firstDigit;
		testCpf = testCpf.concat(firstDigit);
		sum = 0;
		decrement = 11;

		for (int i = 0; i < testCpf.length(); i++) {
			sum += ((int) testCpf.charAt(i) - 48) * decrement;
			decrement--;
		}
		secondDigit = Integer.toString(11 - (sum % 11));
		secondDigit = secondDigit.equals("11") || secondDigit.equals("10") ? "0" : secondDigit;
		testCpf = testCpf.concat(secondDigit);
		if (cpf.equals(testCpf)) {
			return true;
		}
		return false;
	}
}
