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

import br.com.dlcars.model.Dealership;
import br.com.dlcars.model.dto.DealershipDto;
import br.com.dlcars.service.DealershipService;

@RestController
@RequestMapping(value = "/dealerships")
public class DealershipResource {

	@Autowired
	private DealershipService service;

	@GetMapping
	public ResponseEntity<List<Dealership>> findAll() {
		return ResponseEntity.ok().body(this.service.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Dealership> findById(@PathVariable String id) {
		if (id.isEmpty() || id.isBlank()) {
			throw new IllegalArgumentException("The id can't be empty or blank");
		}
		return ResponseEntity.ok().body(this.service.findById(id));
	}

	@PostMapping
	public ResponseEntity<Void> insert(DealershipDto dlDto) {
		Dealership dl = this.service.fromDealershipDto(dlDto);
		dl = this.service.insert(dl);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dl.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody DealershipDto dlDto, @PathVariable String id) {
		if (id.isEmpty() || id.isBlank()) {
			throw new IllegalArgumentException("The id can't be empty or blank");
		}
		Dealership dl = this.service.fromDealershipDto(dlDto);
		dl = this.service.update(dl);
		dl.setId(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		if (id.isEmpty() || id.isBlank()) {
			throw new IllegalArgumentException("The id can't be empty or blank");
		}
		this.service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
