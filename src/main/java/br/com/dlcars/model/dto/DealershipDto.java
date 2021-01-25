package br.com.dlcars.model.dto;

import java.io.Serializable;

import br.com.dlcars.model.Dealership;

public class DealershipDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String phone;
	private String email;
	private String adress;

	public DealershipDto() {

	}

	public DealershipDto(Dealership dealership) {
		this.id = dealership.getId();
		this.name = dealership.getName();
		this.phone = dealership.getPhone();
		this.email = dealership.getEmail();
		this.adress = dealership.getAdress();
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

}
