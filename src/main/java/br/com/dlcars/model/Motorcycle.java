package br.com.dlcars.model;

public class Motorcycle extends Vehicle {

	private static final long serialVersionUID = 1L;

	public Motorcycle() {
		super();
	}

	public Motorcycle(String id, String model, Integer quantity, Double value, Double consumptionPerKm,
			VehicleInsurer carInsurer, String licensePlate) {
		super(id, model, quantity, value, consumptionPerKm, carInsurer, licensePlate);
	}

}
