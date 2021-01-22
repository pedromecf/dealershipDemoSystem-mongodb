package br.com.dlcars.model;

public class Car extends Vehicle {

	private static final long serialVersionUID = 1L;

	public Car() {
		super();
	}

	public Car(String id, String model, Integer quantity, Double value, Double consumptionPerKm,
			VehicleInsurer carInsurer, String licensePlate) {
		super(id, model, quantity, value, consumptionPerKm, carInsurer, licensePlate);
	}

}
