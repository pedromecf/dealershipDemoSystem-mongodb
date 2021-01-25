package br.com.dlcars.model.dto;

import java.io.Serializable;

import br.com.dlcars.model.Dealership;

public class SellerDealershipDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;

	public SellerDealershipDto() {

	}

	public SellerDealershipDto(Dealership dealership) {
		this.id = dealership.getId();
		this.name = dealership.getName();
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

}
