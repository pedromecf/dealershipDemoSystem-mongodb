package br.com.dlcars.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.dlcars.model.dto.ClientVehicleDto;
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
	private ClientVehicleDto vehicle;

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

	public ClientVehicleDto getVehicle() {
		return vehicle;
	}

	public void setVehicle(ClientVehicleDto vehicle) {
		this.vehicle = vehicle;
	}

	public boolean hasAVehicle() {
		if (this.getVehicle().getId() != null) {
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
