package br.com.dlcars.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dlcars.model.Dealership;
import br.com.dlcars.model.Seller;
import br.com.dlcars.model.dto.DealershipDto;
import br.com.dlcars.repository.DealershipRepository;
import br.com.dlcars.service.exception.ObjectNotFoundException;

@Service
public class DealershipService {

	@Autowired
	private DealershipRepository repository;

	public List<Dealership> findAll() {
		return this.repository.findAll();
	}

	public Dealership findById(String id) {
		return this.repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("There's no dealership using this id"));
	}

	public Dealership findBySeller(Seller seller) {
		List<Dealership> dealerships = this.findAll();
		dealerships.stream().filter(x -> x.getId().equals(seller.getDealership().getId())).collect(Collectors.toList());
		if (dealerships.isEmpty()) {
			throw new ObjectNotFoundException("There isn't any dealership with this employee");
		}
		return dealerships.get(0);
	}

	public Dealership insert(Dealership obj) {
		return this.repository.save(obj);
	}

	public Dealership update(Dealership obj) {
		Dealership newObj = new Dealership();
		this.setDealershipData(newObj, obj);
		return this.repository.save(newObj);
	}

	public void deleteById(String id) {
		this.findById(id);
		this.repository.deleteById(id);
	}

	public void setDealershipData(Dealership newObj, Dealership obj) {
		newObj.setId(obj.getId());
		newObj.setName(obj.getName());
		newObj.setPhone(obj.getPhone());
		newObj.setEmail(obj.getEmail());
		newObj.setAdress(obj.getAdress());
	}

	public Dealership fromDealershipDto(DealershipDto objDto) {
		Dealership newObj = new Dealership();
		newObj.setId(objDto.getId());
		newObj.setName(objDto.getName());
		newObj.setPhone(objDto.getPhone());
		newObj.setEmail(objDto.getEmail());
		newObj.setAdress(objDto.getAdress());
		return newObj;
	}

}
