package br.com.dlcars.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.dlcars.model.dto.DealershipClientDto;
import br.com.dlcars.model.dto.VehicleInsurerDto;

@Document
@TypeAlias(value = "Vehicle")
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String type;
	private String brand;
	private String model;
	private String licensePlate;
	private Integer quantity;
	private Double value;
	private Double kmPerLiter;
	private VehicleInsurerDto insurer;
	private DealershipClientDto owner;

	public Vehicle() {

	}

	@PersistenceConstructor
	public Vehicle(String id, String type, String brand, String model, String licensePlate, Integer quantity,
			Double value, Double kmPerLiter, VehicleInsurerDto insurer) {
		this.id = id;
		this.type = type;
		this.brand = brand;
		this.model = model;
		this.licensePlate = licensePlate;
		this.quantity = quantity;
		this.value = value;
		this.kmPerLiter = kmPerLiter;
		this.insurer = insurer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getKmPerLiter() {
		return kmPerLiter;
	}

	public void setKmPerLiter(Double kmPerLiter) {
		this.kmPerLiter = kmPerLiter;
	}

	public VehicleInsurerDto getInsurer() {
		return insurer;
	}

	public void setInsurer(VehicleInsurerDto carInsurer) {
		this.insurer = carInsurer;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public DealershipClientDto getOwner() {
		return owner;
	}

	public void setOwner(DealershipClientDto owner) {
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((licensePlate == null) ? 0 : licensePlate.hashCode());
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
		Vehicle other = (Vehicle) obj;
		if (licensePlate == null) {
			if (other.licensePlate != null)
				return false;
		} else if (!licensePlate.equals(other.licensePlate))
			return false;
		return true;
	}

}
