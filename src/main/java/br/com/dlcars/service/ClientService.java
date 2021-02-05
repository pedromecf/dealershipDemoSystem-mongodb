package br.com.dlcars.service;

import java.util.List;

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
		return this.repository.findAll();
	}

	public Client findById(String id) {
		return this.repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("There isn't a client using this id"));
	}

	public Client findByCpf(String cpf) {
		List<Client> allClients = this.findAll();
		allClients.removeIf(x -> !x.getCpf().equals(cpf));
		if (allClients.isEmpty()) {
			throw new ObjectNotFoundException("There isn't a client using this CPF");
		}
		return allClients.get(0);
	}

	public Client findByCnpj(String cnpj) {
		List<Client> allClients = this.findAll();
		allClients.removeIf(x -> !x.getCnpj().equals(cnpj));
		if (allClients.isEmpty()) {
			throw new ObjectNotFoundException("There isn't a client using this CNPJ");
		}
		return allClients.get(0);
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
