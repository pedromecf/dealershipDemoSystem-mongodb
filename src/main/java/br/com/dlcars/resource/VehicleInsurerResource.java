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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.dlcars.model.VehicleInsurer;
import br.com.dlcars.model.dto.VehicleInsurerDto;
import br.com.dlcars.service.VehicleInsurerService;

@RestController
@RequestMapping(value = "/insurers")
public class VehicleInsurerResource {
	
	@Autowired
	private VehicleInsurerService service;
	
	@GetMapping
	public ResponseEntity<List<VehicleInsurer>> findAll() {
		List<VehicleInsurer> insurers = this.service.findAll();
		return ResponseEntity.ok().body(insurers);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<VehicleInsurer> findById(@PathVariable String id) {
		if (id.isBlank() || id.isEmpty()) {
			throw new IllegalArgumentException("The id can't be blank or empty");
		}
		VehicleInsurer insurer = this.service.findById(id);
		return ResponseEntity.ok().body(insurer);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(VehicleInsurerDto insurerDto) {
		VehicleInsurer insurer = this.service.fromInsurerDto(insurerDto);
		insurer = this.service.insert(insurer);
		insurer.setName(insurerDto.getName());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(insurer.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody VehicleInsurerDto insurerDto, @PathVariable String id) {
		if (id.isBlank() || id.isEmpty()) {
			throw new IllegalArgumentException("The id can't be blank or empty");
		}
		VehicleInsurer insurer = this.service.fromInsurerDto(insurerDto);
		insurer.setId(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		if (id.isBlank() || id.isEmpty()) {
			throw new IllegalArgumentException("The id can't be blank or empty");
		}
		this.service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
