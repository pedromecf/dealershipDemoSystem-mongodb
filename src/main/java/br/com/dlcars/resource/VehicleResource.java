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
		return ResponseEntity.ok().body(this.service.findAll());
	}

	@GetMapping(value = "/types")
	public ResponseEntity<List<Vehicle>> findAllByType(@RequestParam(value = "type") String type) {
		if (type.isEmpty() || type.isBlank()) {
			throw new IllegalArgumentException("The type can't be empty or blank");
		}
		type = URL.decodeParameter(type);
		return ResponseEntity.ok().body(this.service.findAllByType(type));
	}

	@GetMapping(value = "/brands")
	public ResponseEntity<List<Vehicle>> findAllByBrand(@RequestParam(value = "brand") String brand) {
		if (brand.isEmpty() || brand.isBlank()) {
			throw new IllegalArgumentException("The brand can't be empty or blank");
		}
		brand = URL.decodeParameter(brand);
		return ResponseEntity.ok().body(this.service.findAllByBrand(brand));
	}

	@GetMapping(value = "/models")
	public ResponseEntity<List<Vehicle>> findAllByModel(@RequestParam(value = "model") String model) {
		if (model.isEmpty() || model.isBlank()) {
			throw new IllegalArgumentException("The model can't be empty or blank");
		}
		model = URL.decodeParameter(model);
		return ResponseEntity.ok().body(this.service.findAllByModel(model));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Vehicle> findById(@PathVariable String id) {
		if (id.isEmpty() || id.isBlank()) {
			throw new IllegalArgumentException("The id can't be empty or blank");
		}
		return ResponseEntity.ok().body(this.service.findById(id));
	}

	@GetMapping(value = "/plates")
	public ResponseEntity<Vehicle> findByLicensePlate(@RequestParam(value = "plate") String licensePlate) {
		if (licensePlate.isEmpty() || licensePlate.isBlank()) {
			throw new IllegalArgumentException("The license plate can't be blank or empty");
		} else if (licensePlate.length() < 9 || licensePlate.length() > 9) {
			throw new IllegalArgumentException("The license plate must follow the pattern (Example: XXXX-XXXX)");
		}
		return ResponseEntity.ok().body(this.service.findByLicensePlate(licensePlate));
	}

	@PostMapping
	public ResponseEntity<Void> insert(VehicleDto vehicleDto) {
		String id = vehicleDto.getId();
		Vehicle vehicle = this.service.fromVehicleDto(vehicleDto, id);
		vehicle = this.service.insert(vehicle);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vehicle.getId())
				.toUri();
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
		if (id.isEmpty() || id.isBlank()) {
			throw new IllegalArgumentException("The id can't be blank or empty");
		}
		this.service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/brands")
	public ResponseEntity<Void> deleteByBrand(@RequestParam(value = "brand") String brand) {
		if (brand.isEmpty() || brand.isEmpty()) {
			throw new IllegalArgumentException("The parameter can't be empty or blank");
		}
		brand = URL.decodeParameter(brand);
		this.service.deleteByBrand(brand);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/models")
	public ResponseEntity<Void> deleteByModel(@RequestParam(value = "model") String model) {
		if (model.isEmpty() || model.isBlank()) {
			throw new IllegalArgumentException("The parameter can't be empty or blank");
		}
		model = URL.decodeParameter(model);
		this.service.deleteByModel(model);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/plates")
	public ResponseEntity<Void> deleteByLicensePlate(@RequestParam(value = "plate") String licensePlate) {
		if (licensePlate.isEmpty() || licensePlate.isBlank()) {
			throw new IllegalArgumentException("The parameter can't be empty or blank");
		} else if (licensePlate.length() < 9 || licensePlate.length() > 9) {
			throw new IllegalArgumentException("The license plate must follow the pattern (Example: XXXX-XXXX)");
		}
		this.service.deleteByLicensePlate(licensePlate);
		return ResponseEntity.noContent().build();
	}
}
