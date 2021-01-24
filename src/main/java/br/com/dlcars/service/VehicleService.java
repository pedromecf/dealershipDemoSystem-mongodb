package br.com.dlcars.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dlcars.model.Vehicle;
import br.com.dlcars.model.dto.VehicleDto;
import br.com.dlcars.repository.VehicleRepository;
import br.com.dlcars.service.exception.ObjectNotFoundException;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository repository;

	public List<Vehicle> findAll() {
		return this.repository.findAll();
	}

	public List<Vehicle> findAllByType(String type) {
		List<Vehicle> allVehicles = this.repository.findAll();
		List<Vehicle> typeVehicles = new ArrayList<>();
		for (Vehicle v : allVehicles) {
			if (v.getType().toLowerCase().contains(type.toLowerCase())) {
				typeVehicles.add(v);
			}
		}
		if (typeVehicles.isEmpty()) {
			throw new ObjectNotFoundException("The parameter used in search doesn't exists");
		}
		return typeVehicles;
	}

	public List<Vehicle> findAllByBrand(String brand) {
		List<Vehicle> allVehicles = this.repository.findAll();
		List<Vehicle> brandVehicles = new ArrayList<>();
		for (Vehicle v : allVehicles) {
			if (v.getBrand().toLowerCase().contains(brand.toLowerCase())) {
				brandVehicles.add(v);
			}
		}
		if (brandVehicles.isEmpty()) {
			throw new ObjectNotFoundException("The vehicle brand doens't exists");
		}
		return brandVehicles;
	}

	public List<Vehicle> findAllByModel(String model) {
		List<Vehicle> allVehicles = this.findAll();
		List<Vehicle> modelList = new ArrayList<>();
		for (Vehicle v : allVehicles) {
			if (v.getModel().toLowerCase().contains(model.toLowerCase())) {
				modelList.add(v);
			}
		}
		if (modelList.isEmpty()) {
			throw new ObjectNotFoundException("There aren't any vehicles of this model");
		}
		return modelList;
	}

	public Vehicle findById(String id) {
		Optional<Vehicle> vehicles = this.repository.findById(id);
		return vehicles.orElseThrow(() -> new ObjectNotFoundException("There isn't a vehicle using this id"));
	}

	public Vehicle findByLicensePlate(String licensePlate) {
		Vehicle vehicle = new Vehicle();
		List<Vehicle> vehicles = this.findAll();
		for (Vehicle v : vehicles) {
			if (v.getLicensePlate().toLowerCase().equals(licensePlate.toLowerCase())) {
				this.setVehicleData(v, vehicle);
			}
		}
		if (vehicle.getId() == null) {
			throw new ObjectNotFoundException("There isn't a vehicle using this license plate");
		}
		return vehicle;
	}

	public Vehicle insert(Vehicle obj) {
		return this.repository.save(obj);
	}

	public Vehicle update(Vehicle obj) {
		Vehicle newObj = findById(obj.getId());
		this.setVehicleData(obj, newObj);
		return this.repository.save(newObj);
	}

	public void deleteById(String id) {
		findById(id);
		this.repository.deleteById(id);
	}

	public void deleteByBrand(String brand) {
		List<Vehicle> allVehicles = this.findAll();
		List<Vehicle> brandVehicles = new ArrayList<>();
		for (Vehicle v : allVehicles) {
			if (v.getBrand().toLowerCase().equals(brand.toLowerCase())) {
				brandVehicles.add(v);
			}
		}
		if (brandVehicles.isEmpty()) {
			throw new ObjectNotFoundException("The brand doesn't exists");
		}
		this.repository.deleteAll(brandVehicles);
	}

	public void deleteByModel(String model) {
		List<Vehicle> allVehicles = this.findAll();
		List<Vehicle> modelVehicles = new ArrayList<>();
		for (Vehicle v : allVehicles) {
			if (v.getModel().toLowerCase().equals(model.toLowerCase())) {
				modelVehicles.add(v);
			}
		}
		if (modelVehicles.isEmpty()) {
			throw new ObjectNotFoundException("The vehicle model doesn't exists");
		}
		this.repository.deleteAll(modelVehicles);
	}

	public void deleteByLicensePlate(String licensePlate) {
		List<Vehicle> allVehicles = this.repository.findAll();
		Vehicle vehicle = new Vehicle();
		for (Vehicle v : allVehicles) {
			if (v.getLicensePlate().toUpperCase().equals(licensePlate.toUpperCase())) {
				this.setVehicleData(v, vehicle);
			}
		}
		if (vehicle.getId() == null) {
			throw new ObjectNotFoundException("There isn't a vehicle using this license plate");
		}
		this.repository.delete(vehicle);
	}

	public void setVehicleData(Vehicle obj, Vehicle newObj) {
		newObj.setId(obj.getId());
		newObj.setType(obj.getType());
		newObj.setBrand(obj.getBrand());
		newObj.setModel(obj.getModel());
		newObj.setQuantity(obj.getQuantity());
		newObj.setValue(obj.getValue());
		newObj.setKmPerLiter(obj.getKmPerLiter());
		newObj.setInsurer(obj.getInsurer());
		newObj.setLicensePlate(obj.getLicensePlate());
	}

	public Vehicle fromVehicleDto(VehicleDto objDto, String id) {
		Vehicle obj = new Vehicle();
		obj.setId(objDto.getId());
		obj.setType(objDto.getType());
		obj.setBrand(objDto.getBrand());
		obj.setModel(objDto.getModel());
		obj.setLicensePlate(objDto.getLicensePlate());
		obj.setQuantity(objDto.getQuantity());
		obj.setValue(objDto.getValue());
		obj.setKmPerLiter(objDto.getKmPerLiter());
		Vehicle v = findById(objDto.getId());
		obj.setInsurer(v.getInsurer());
		return obj;
	}

}
