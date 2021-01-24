package br.com.dlcars.model.dto;

import java.io.Serializable;

import br.com.dlcars.model.Vehicle;

public class VehicleDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String type;
	private String brand;
	private String model;
	private Integer quantity;
	private Double value;
	private Double kmPerLiter;
	private String licensePlate;

	public VehicleDto() {
		
	}

	public VehicleDto(Vehicle vehicle) {
		this.id = vehicle.getId();
		this.type = vehicle.getType();
		this.brand = vehicle.getBrand();
		this.model = vehicle.getModel();
		this.quantity = vehicle.getQuantity();
		this.value = vehicle.getValue();
		this.kmPerLiter = vehicle.getKmPerLiter();
		this.licensePlate = vehicle.getLicensePlate();
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

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
}
