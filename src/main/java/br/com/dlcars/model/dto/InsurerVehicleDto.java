package br.com.dlcars.model.dto;

import java.io.Serializable;

public class InsurerVehicleDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String type;
	private String brand;
	private String model;

	public InsurerVehicleDto() {
		
	}

	public InsurerVehicleDto(VehicleDto vehicleDto) {
		this.id = vehicleDto.getId();
		this.type = vehicleDto.getType();
		this.brand = vehicleDto.getBrand();
		this.model = vehicleDto.getModel();
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
	
}
