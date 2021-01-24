package br.com.dlcars.model.dto;

import java.io.Serializable;

import br.com.dlcars.model.Client;

public class ClientDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String rg;
	private String cpf;
	private String cnpj;
	private String email;
	private String adress;
	private Double balance;

	public ClientDto() {

	}

	public ClientDto(Client client) {
		if (client.getCpf().isBlank()) {
			this.id = client.getId();
			this.name = client.getName();
			this.rg = client.getRg();
			this.cpf = "";
			this.cnpj = client.getCnpj();
			this.email = client.getEmail();
			this.adress = client.getAdress();
			this.balance = client.getBalance();
		} else {
			this.id = client.getId();
			this.name = client.getName();
			this.rg = client.getRg();
			this.cpf = client.getCpf();
			this.cnpj = "";
			this.email = client.getEmail();
			this.adress = client.getAdress();
			this.balance = client.getBalance();
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

}
