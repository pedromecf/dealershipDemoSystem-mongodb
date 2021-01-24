package br.com.dlcars.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.dlcars.model.util.CnpjValidation;
import br.com.dlcars.model.util.CpfValidation;

@Document
@TypeAlias(value = "Client")
public class Client implements Serializable, CpfValidation, CnpjValidation {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String name;
	private String rg;
	private String cpf;
	private String cnpj;
	private String email;
	private String adress;
	private Double balance;

	public Client() {

	}
	
	public Client(String id, String name, String rg, String cpf, String cnpj, String email, String adress,
			Double balance) {
		if (cpf.isBlank()) {
			this.id = id;
			this.name = name;
			this.rg = rg;
			this.cpf = "";
			this.cnpj = cnpj;
			this.email = email;
			this.adress = adress;
			this.balance = balance;
		} else {
			this.id = id;
			this.name = name;
			this.rg = rg;
			this.cpf = cpf;
			this.cnpj = "";
			this.email = email;
			this.adress = adress;
			this.balance = balance;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public boolean validateCnpj(String cnpj) {
		if (cnpj.length() != 14 || cnpj.equals("00000000000000") || cnpj.equals("11111111111111") || cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
				|| cnpj.equals("44444444444444") || cnpj.equals("55555555555555") || cnpj.equals("66666666666666")
				|| cnpj.equals("77777777777777") || cnpj.equals("88888888888888") || cnpj.equals("99999999999999")) {
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
			decrement --;
		}
		decrement = 9;
		for (int i = 0; i < cnpjBlock2.length(); i++) {
			sum += ((int) cnpjBlock2.charAt(i) - 48) * decrement;
			decrement --;
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
			decrement --;
		}
		decrement = 9;
		for (int i = 0; i < cnpjBlock2.length(); i++) {
			sum += ((int) cnpjBlock2.charAt(i) - 48) * decrement;
			decrement --;
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

	@Override
	 public boolean validateCpf(String cpf) {
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
			decrement --;
		}
		firstDigit = Integer.toString(11 - (sum % 11));
		firstDigit = firstDigit.equals("11") || firstDigit.equals("10") ? "0"  : firstDigit;
		testCpf = testCpf.concat(firstDigit);
		sum = 0;
		decrement = 11;
		
		for (int i = 0; i < testCpf.length(); i++) {
			sum += ((int) testCpf.charAt(i) - 48) * decrement;
			decrement --;
		}
		secondDigit = Integer.toString(11 - (sum % 11));
		secondDigit = secondDigit.equals("11") || secondDigit.equals("10") ? "0"  : secondDigit; 
		testCpf = testCpf.concat(secondDigit);
		if (cpf.equals(testCpf)) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		return true;
	}

}
