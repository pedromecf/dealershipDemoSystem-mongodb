package br.com.dlcars.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.dlcars.model.dto.ClientDto;
import br.com.dlcars.model.dto.SellerDto;
import br.com.dlcars.model.dto.VehicleDto;
import br.com.dlcars.model.util.Util;
import br.com.dlcars.resource.exception.ObjectUnavailableException;

@Document
@TypeAlias(value = "Dealership")
public class Dealership implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String name;
	private String phone;
	private String email;
	private String adress;
	private OrderVehicle order;
	private List<SellerDto> employess = new ArrayList<>();
	private List<VehicleDto> vehicles = new ArrayList<>();

	public Dealership() {

	}

	public Dealership(String id, String name, String phone, String email, String adress) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.adress = adress;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public OrderVehicle getOrder() {
		return order;
	}

	public void setOrder(OrderVehicle order) {
		this.order = order;
	}

	public List<SellerDto> getEmployess() {
		return employess;
	}

	public List<VehicleDto> getVehicles() {
		return vehicles;
	}

	public boolean vehicleAvailable(Vehicle vehicle) {
		for (VehicleDto v : this.getVehicles()) {
			if (v.getId().equals(vehicle.getId()) && v.getQuantity() > 0) {
				return true;
			}
		}
		return false;
	}

	public OrderVehicle sellVehicle(ClientDto clientDto, Vehicle vehicle, String status)
			throws ParseException {
		if (!vehicleAvailable(vehicle)) {
			throw new ObjectUnavailableException("The vehicle is unavailable");
		}
		Client client = new Client();
		Util.fromClientDto(clientDto, client);
		if (client.getBalance() >= vehicle.getValue()) {
			OrderVehicle order = new OrderVehicle();
			for (VehicleDto v : this.vehicles) {
				if (v.getId().equals(vehicle.getId())) {
					v.setQuantity(v.getQuantity() - 1);
				}
			}
			order.getStatus();
			return order.executeOrder(client, vehicle, order, status);
		} else {
			throw new IllegalStateException("The client don't have enough balance in order to conclude the sale");
		}
	}

	public void buyVehicle(ClientDto clientDto, Vehicle vehicle) {
		Client client = new Client();
		Util.fromClientDto(clientDto, client);
		if (client.hasAVehicle()) {
			client.setBalance(client.getBalance() + vehicle.getValue());
			client.setVehicle(null);
			vehicle.setOwner(null);
			this.vehicles.add(new VehicleDto(vehicle));
		} else {
			throw new IllegalStateException("The client don't have a vehicle to sell");
		}
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
		Dealership other = (Dealership) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
