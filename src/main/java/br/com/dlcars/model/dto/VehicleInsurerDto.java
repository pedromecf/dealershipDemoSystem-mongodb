package br.com.dlcars.model.dto;

import java.io.Serializable;

import br.com.dlcars.model.VehicleInsurer;

public class VehicleInsurerDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	
	public VehicleInsurerDto() {
		
	}

	public VehicleInsurerDto(VehicleInsurer insurer) {
		this.id = insurer.getId();
		this.name = insurer.getName();
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
