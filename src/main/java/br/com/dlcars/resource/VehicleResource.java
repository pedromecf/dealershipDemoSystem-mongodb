package br.com.dlcars.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.dlcars.model.Vehicle;
import br.com.dlcars.model.dto.VehicleDto;
import br.com.dlcars.resource.util.URL;
import br.com.dlcars.service.VehicleService;

@RestController
@RequestMapping(value = "/vehicles")
public class VehicleResource {
	
	@Autowired
	private VehicleService service;
	
	@GetMapping
	public ResponseEntity<List<Vehicle>> findAll() {
		List<Vehicle> vehicles = this.service.findAll();
		return ResponseEntity.ok().body(vehicles);
	}
	
	@GetMapping(value = "/types")
	public ResponseEntity<List<Vehicle>> findAllByType(@RequestParam(value = "type", required = true) String type) {
			type = URL.decodeParameter(type);
			List<Vehicle> vehicles = this.service.findAllByType(type);
			return ResponseEntity.ok().body(vehicles);
	}
	
	@GetMapping(value = "/brands")
	public ResponseEntity<List<Vehicle>> findAllByBrand(@RequestParam(value = "brand") String brand) {
		brand = URL.decodeParameter(brand);
		List<Vehicle> vehicles = this.service.findAllByBrand(brand);
		return ResponseEntity.ok().body(vehicles);
	}
	
	@GetMapping(value = "/models")
	public ResponseEntity<List<Vehicle>> findAllByModel(@RequestParam(value = "model") String model) {
		model = URL.decodeParameter(model);
		System.out.println(model);
		List<Vehicle> vehicles = this.service.findAllByModel(model);
		return ResponseEntity.ok().body(vehicles);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Vehicle> findById(@PathVariable String id) {
		Vehicle obj = this.service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/plates")
	public ResponseEntity<Vehicle> findByLicensePlate(@RequestParam(value = "plate") String licensePlate) {
		Vehicle vehicle = this.service.findByLicensePlate(licensePlate);
		return ResponseEntity.ok().body(vehicle);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(VehicleDto vehicleDto) {
		String id = vehicleDto.getId();
		Vehicle vehicle = this.service.fromVehicleDto(vehicleDto, id);
		vehicle = this.service.insert(vehicle);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vehicle.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody VehicleDto vehicleDto, @PathVariable String id) {
		Vehicle vehicle = this.service.fromVehicleDto(vehicleDto, id);
		vehicle.setId(id);
		vehicle = this.service.update(vehicle);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		this.service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/brands") 
	public ResponseEntity<Void> deleteByBrand(@RequestParam(value = "brand") String brand) {
		brand = URL.decodeParameter(brand);
		this.service.deleteByBrand(brand);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/models") 
	public ResponseEntity<Void> deleteByModel(@RequestParam(value = "model") String model) {
		model = URL.decodeParameter(model);
		this.service.deleteByModel(model);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/plates")
	public ResponseEntity<Void> deleteByLicensePlate(@RequestParam(value = "plate") String licensePlate) {
		this.service.deleteByLicensePlate(licensePlate);
		return ResponseEntity.noContent().build();
	}
}
