package br.com.dlcars.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.dlcars.model.dto.SellerDealershipDto;

@Document
@TypeAlias(value = "Seller")
public class Seller implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String name;
	private String charge;
	private String email;
	private Double salary;
	private SellerDealershipDto dealership;

	public Seller() {

	}

	public Seller(String id, String name, String charge, String email, Double salary, SellerDealershipDto dealership) {
		this.id = id;
		this.name = name;
		this.charge = charge;
		this.email = email;
		this.salary = salary;
		this.dealership = dealership;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public SellerDealershipDto getDealership() {
		return dealership;
	}

	public void setDealership(SellerDealershipDto dealership) {
		this.dealership = dealership;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Seller other = (Seller) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
