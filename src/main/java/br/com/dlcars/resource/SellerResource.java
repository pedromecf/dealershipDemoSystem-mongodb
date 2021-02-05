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

import br.com.dlcars.model.Dealership;
import br.com.dlcars.model.Seller;
import br.com.dlcars.model.dto.SellerDto;
import br.com.dlcars.resource.exception.InvalidParamException;
import br.com.dlcars.resource.util.URL;
import br.com.dlcars.service.SellerService;

@RestController
@RequestMapping(value = "/sellers")
public class SellerResource {

	@Autowired
	private SellerService service;

	@GetMapping
	public ResponseEntity<List<Seller>> findAll() {
		return ResponseEntity.ok().body(this.service.findAll());
	}

	@PostMapping(value = "/dealerships")
	public ResponseEntity<List<Seller>> findAllByDealership(@RequestBody Dealership dl) {
		if (dl.getId() == null) {
			throw new InvalidParamException("The dealership doesn't exists");
		}
		return ResponseEntity.ok().body(this.service.findAllByDealership(dl));
	}


	@GetMapping(value = "/charges")
	public ResponseEntity<List<Seller>> findAllByCharge(@RequestParam(value = "chg") String charge) {
		if (charge.isBlank() || charge.isEmpty()) {
			throw new InvalidParamException("The charge can't be empty or blank");
		}
		charge = URL.decodeParameter(charge);
		return ResponseEntity.ok().body(this.service.findAllByCharge(charge));
	}


	@GetMapping(value = "/{id}")
	public ResponseEntity<Seller> findById(@PathVariable String id) {
		if (id.isEmpty() || id.isBlank()) {
			throw new InvalidParamException("The id can't be empty or blank");
		}
		return ResponseEntity.ok().body(this.service.findById(id));
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody SellerDto slDto) {
		Seller sl = this.service.fromSellerDto(slDto);
		sl = this.service.insert(sl);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sl.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody SellerDto slDto, @PathVariable String id) {
		if (id.isEmpty() || id.isBlank()) {
			throw new InvalidParamException("The id can't be empty or blank");
		}
		Seller sl = this.service.fromSellerDto(slDto);
		sl.setId(id);
		sl = this.service.update(sl);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		if (id.isEmpty() || id.isBlank()) {
			throw new InvalidParamException("The id can't be empty or blank");
		}
		this.service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
