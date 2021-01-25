package br.com.dlcars.model.util;

public interface CnpjValidation {

	default boolean validateCnpj(String cnpj) {
		if (cnpj.length() != 14 || cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
				|| cnpj.equals("22222222222222") || cnpj.equals("33333333333333") || cnpj.equals("44444444444444")
				|| cnpj.equals("55555555555555") || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
				|| cnpj.equals("88888888888888") || cnpj.equals("99999999999999")) {
			throw new IllegalArgumentException("Invalid CNPJ");
		}
		String testCnpj = "";
		String cnpjBlock1 = cnpj.substring(0, 4);
		String cnpjBlock2 = cnpj.substring(4, 12);
		String firstDigit;
		String secondDigit;
		int sum = 0;
		int decrement = 5;
		for (int i = 0; i < cnpjBlock1.length(); i++) {
			sum += ((int) cnpjBlock1.charAt(i) - 48) * decrement;
			decrement--;
		}
		decrement = 9;
		for (int i = 0; i < cnpjBlock2.length(); i++) {
			sum += ((int) cnpjBlock2.charAt(i) - 48) * decrement;
			decrement--;
		}
		firstDigit = Integer.toString(11 - (sum % 11));
		firstDigit = firstDigit.equals("10") || firstDigit.equals("11") ? "0" : firstDigit;
		cnpjBlock1 = cnpjBlock1.concat(Character.toString(cnpjBlock2.charAt(0)));
		cnpjBlock2 = cnpjBlock2.substring(1, 8);
		cnpjBlock2 = cnpjBlock2.concat(firstDigit);

		sum = 0;
		decrement = 6;
		for (int i = 0; i < cnpjBlock1.length(); i++) {
			sum += ((int) cnpjBlock1.charAt(i) - 48) * decrement;
			decrement--;
		}
		decrement = 9;
		for (int i = 0; i < cnpjBlock2.length(); i++) {
			sum += ((int) cnpjBlock2.charAt(i) - 48) * decrement;
			decrement--;
		}
		secondDigit = Integer.toString(11 - (sum % 11));
		secondDigit = secondDigit.equals("10") || secondDigit.equals("11") ? "0" : secondDigit;
		cnpjBlock2 = cnpjBlock2.concat(secondDigit);

		testCnpj = testCnpj.concat((cnpjBlock1.concat(cnpjBlock2)));
		if (cnpj.equals(testCnpj)) {
			return true;
		}
		return false;
	}

}
