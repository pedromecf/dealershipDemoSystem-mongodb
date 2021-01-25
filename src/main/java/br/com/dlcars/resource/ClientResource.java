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

import br.com.dlcars.model.Client;
import br.com.dlcars.model.dto.ClientDto;
import br.com.dlcars.service.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

	@Autowired
	private ClientService service;

	@GetMapping
	public ResponseEntity<List<Client>> findAll() {
		List<Client> clients = this.service.findAll();
		return ResponseEntity.ok().body(clients);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findById(@PathVariable String id) {
		if (id.isEmpty() || id.isBlank()) {
			throw new IllegalArgumentException("The id can't be empty or blank");
		}
		Client client = this.service.findById(id);
		return ResponseEntity.ok().body(client);
	}

	@GetMapping(value = "/naturals")
	public ResponseEntity<Client> findByCpf(@RequestParam(value = "cpf") String cpf) {
		if (cpf.isEmpty() || cpf.isBlank()) {
			throw new IllegalArgumentException("The CNPJ can't be empty or blank");
		}
		Client client = this.service.findByCpf(cpf);
		return ResponseEntity.ok().body(client);
	}

	@GetMapping(value = "/legals")
	public ResponseEntity<Client> findByCnpj(@RequestParam(value = "cnpj") String cnpj) {
		if (cnpj.isEmpty() || cnpj.isBlank()) {
			throw new IllegalArgumentException("The CNPJ can't be empty or blank");
		}
		Client client = this.service.findByCnpj(cnpj);
		return ResponseEntity.ok().body(client);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody ClientDto clientDto, @PathVariable String id) {
		if (id.isEmpty() || id.isBlank()) {
			throw new IllegalArgumentException("The id can't be empty or blank");
		}
		Client client = this.service.fromClientDto(clientDto);
		client.setId(id);
		client = this.service.update(client);
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Void> insert(ClientDto clientDto) {
		Client client = this.service.fromClientDto(clientDto);
		client = this.service.insert(client);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		this.service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/naturals")
	public ResponseEntity<Void> deleteByCpf(@RequestParam(value = "cpf") String cpf) {
		this.service.deleteByCpf(cpf);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/legals")
	public ResponseEntity<Void> deleteByCnpj(@RequestParam(value = "cnpj") String cnpj) {
		this.service.deleteByCnpj(cnpj);
		return ResponseEntity.noContent().build();
	}

}
