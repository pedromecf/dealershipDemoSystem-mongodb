package br.com.dlcars.service;

import java.util.List;

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
		allVehicles.removeIf(x -> !x.getType().toLowerCase().equals(type.toLowerCase()));
		if (allVehicles.isEmpty()) {
			throw new ObjectNotFoundException("There are no vehicles of this type");
		}
		return allVehicles;
	}

	public List<Vehicle> findAllByBrand(String brand) {
		List<Vehicle> allVehicles = this.repository.findAll();
		allVehicles.removeIf(x -> !x.getBrand().toLowerCase().equals(brand.toLowerCase()));
		if (allVehicles.isEmpty()) {
			throw new ObjectNotFoundException("There are no vehicles of this brand");
		}
		return allVehicles;
	}

	public List<Vehicle> findAllByModel(String model) {
		List<Vehicle> allVehicles = this.findAll();
		allVehicles.removeIf(x -> x.getModel().toLowerCase().equals(model.toLowerCase()));
		if (allVehicles.isEmpty()) {
			throw new ObjectNotFoundException("There are no vehicles of this model");
		}
		return allVehicles;
	}

	public Vehicle findById(String id) {
		return this.repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("There isn't a vehicle using this id"));
	}

	public Vehicle findByLicensePlate(String licensePlate) {
		List<Vehicle> allVehicles = this.findAll();
		allVehicles.removeIf(x -> !x.getLicensePlate().equals(licensePlate));
		if (allVehicles.isEmpty()) {
			throw new ObjectNotFoundException("There isn't a vehicle using this license plate");
		}
		return allVehicles.get(0);
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
		allVehicles.removeIf(x -> !x.getBrand().toLowerCase().equals(brand.toLowerCase()));
		if (allVehicles.isEmpty()) {
			throw new ObjectNotFoundException("The brand doesn't exists");
		}
		this.repository.deleteAll(allVehicles);
	}

	public void deleteByModel(String model) {
		List<Vehicle> allVehicles = this.findAll();
		allVehicles.removeIf(x -> !x.getModel().toLowerCase().equals(model.toLowerCase()));
		if (allVehicles.isEmpty()) {
			throw new ObjectNotFoundException("The vehicle model doesn't exists");
		}
		this.repository.deleteAll(allVehicles);
	}

	public void deleteByLicensePlate(String licensePlate) {
		List<Vehicle> allVehicles = this.repository.findAll();
		allVehicles.removeIf(x -> !x.getLicensePlate().equals(licensePlate));
		if (allVehicles.isEmpty()) {
			throw new ObjectNotFoundException("There isn't a vehicle using this license plate");
		}
		this.repository.deleteAll(allVehicles);
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
