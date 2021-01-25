package br.com.dlcars.model.dto;

import java.io.Serializable;

import br.com.dlcars.model.Seller;

public class SellerDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String charge;
	private String afiliation;

	public SellerDto() {

	}

	public SellerDto(Seller seller) {
		this.id = seller.getId();
		this.name = seller.getName();
		this.charge = seller.getCharge();
		this.afiliation = seller.getDealership().getName();
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

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getAfiliation() {
		return afiliation;
	}

	public void setAfiliation(String afiliation) {
		this.afiliation = afiliation;
	}

}
