package br.com.dlcars.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dlcars.model.Client;
import br.com.dlcars.model.dto.ClientDto;
import br.com.dlcars.repository.ClientRepository;
import br.com.dlcars.service.exception.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public List<Client> findAll() {
		List<Client> clients = this.repository.findAll();
		return clients;
	}

	public Client findById(String id) {
		Optional<Client> client = this.repository.findById(id);
		return client.orElseThrow(() -> new ObjectNotFoundException("There isn't a client using this id"));
	}

	public Client findByCpf(String cpf) {
		List<Client> allClients = this.findAll();
		Client client = new Client();
		for (Client c : allClients) {
			if (c.getCpf().equals(cpf)) {
				setClientData(client, c);
			}
		}
		if (client.getId() == null) {
			throw new ObjectNotFoundException("There isn't a client using this CPF");
		}
		return client;
	}

	public Client findByCnpj(String cnpj) {
		List<Client> allClients = this.findAll();
		Client client = new Client();
		for (Client c : allClients) {
			if (c.getCnpj().equals(cnpj)) {
				setClientData(client, c);
			}
		}
		if (client.getId() == null) {
			throw new ObjectNotFoundException("There isn't a client using this CNPJ");
		}
		return client;
	}

	public Client insert(Client obj) {
		return this.repository.save(obj);
	}

	public Client update(Client obj) {
		Client newObj = new Client();
		this.setClientData(newObj, obj);
		return this.repository.save(newObj);
	}

	public void deleteById(String id) {
		this.repository.deleteById(id);
	}

	public void deleteByCpf(String cpf) {
		Client client = this.findByCpf(cpf);
		this.repository.delete(client);
	}

	public void deleteByCnpj(String cnpj) {
		Client client = this.findByCnpj(cnpj);
		this.repository.delete(client);
	}

	public void setClientData(Client newObj, Client obj) {
		newObj.setId(obj.getId());
		newObj.setName(obj.getName());
		newObj.setRg(obj.getRg());
		newObj.setCpf(obj.getCpf());
		newObj.setCnpj(obj.getCnpj());
		newObj.setEmail(obj.getEmail());
		newObj.setAdress(obj.getAdress());
		newObj.setBalance(obj.getBalance());
	}

	public Client updateClientData(Client obj) {
		Client newObj = this.findById(obj.getId());
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		newObj.setAdress(obj.getAdress());
		newObj.setBalance(obj.getBalance());
		return newObj;
	}

	public Client fromClientDto(ClientDto objDto) {
		Client obj = new Client(objDto.getId(), objDto.getName(), objDto.getRg(), objDto.getCpf(), objDto.getCnpj(),
				objDto.getEmail(), objDto.getAdress(), objDto.getBalance());
		return obj;

	}

}
