package br.com.dlcars.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

import br.com.dlcars.model.dto.ClientDto;
import br.com.dlcars.model.dto.DealershipClientDto;
import br.com.dlcars.model.enums.OrderVehicleStatus;
import br.com.dlcars.model.util.Util;

public class OrderVehicle implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String clientId;
	private Instant orderDate;
	private Date deliveryDate;
	private Vehicle vehicle;
	private Double cost;
	private OrderVehicleStatus status;

	public OrderVehicle() {

	}

	public OrderVehicle(String id, Instant orderDate, Date deliveryDate, Vehicle vehicle, OrderVehicleStatus status) {
		this.id = id;
		this.clientId = vehicle.getOwner().getId();
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.vehicle = vehicle;
		this.cost = vehicle.getValue();
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Instant getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Instant orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public OrderVehicleStatus getStatus() {
		return status;
	}

	public void setStatus(OrderVehicleStatus status) {
		this.status = status;
	}

	public OrderVehicle executeOrder(Client client, Vehicle vehicle, OrderVehicle order, OrderVehicleStatus status) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("Brazil"));
		client.setVehicle(Util.toClientVehicleDto(vehicle));
		client.setBalance(client.getBalance() - vehicle.getValue());
		vehicle.setOwner(new DealershipClientDto(new ClientDto(client)));
		order.setClientId(client.getId());
		order.setCost(vehicle.getValue());
		order.setOrderDate(Instant.now());
		order.setStatus(OrderVehicleStatus.valueOf("PAID"));
		try {
			order.setDeliveryDate(sdf.parse(Long.toString(System.currentTimeMillis() + 72 * 60 * 60 * 1000)));
		} catch (ParseException e) {
			throw new IllegalStateException("Error at date while ordering the vehicle");
		}
		return order;
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
		OrderVehicle other = (OrderVehicle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
