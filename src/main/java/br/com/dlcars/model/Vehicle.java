package br.com.dlcars.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public abstract class Vehicle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String model;
	private Integer quantity;
	private Double value;
	private Double consumptionPerKm;
	private VehicleInsurer carInsurer;
	private String licensePlate;

	public Vehicle() {

	}

	public Vehicle(String id, String model, Integer quantity, Double value, Double consumptionPerKm,
			VehicleInsurer carInsurer, String licensePlate) {
		this.id = id;
		this.model = model;
		this.quantity = quantity;
		this.value = value;
		this.consumptionPerKm = consumptionPerKm;
		this.carInsurer = carInsurer;
		this.licensePlate = licensePlate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Double getConsumptionPerKm() {
		return consumptionPerKm;
	}

	public void setConsumptionPerKm(Double consumptionPerKm) {
		this.consumptionPerKm = consumptionPerKm;
	}

	public VehicleInsurer getCarInsurer() {
		return carInsurer;
	}

	public void setCarInsurer(VehicleInsurer carInsurer) {
		this.carInsurer = carInsurer;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public boolean vehicleAvailable(Vehicle vehicle) {
		if (vehicle.getQuantity() == 0) {
			return false;
		}
		return true;
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
