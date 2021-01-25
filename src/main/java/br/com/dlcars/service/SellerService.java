package br.com.dlcars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dlcars.model.Seller;
import br.com.dlcars.model.dto.SellerDto;
import br.com.dlcars.repository.SellerRepository;
import br.com.dlcars.service.exception.ObjectNotFoundException;

@Service
public class SellerService {

	@Autowired
	private SellerRepository repository;

	public List<Seller> findAll() {
		return this.repository.findAll();
	}

	public List<Seller> findAllByDealership(String id) {
		List<Seller> allSellers = this.findAll();
		allSellers.removeIf(x -> !x.getDealership().getId().equals(id));
		if (allSellers.isEmpty()) {
			throw new ObjectNotFoundException("The department id doesn't exists");
		}
		return allSellers;
	}

	public List<Seller> findAllByDealershipAndCharge(String id, String charge) {
		List<Seller> dlSellers = this.findAllByDealership(id);
		dlSellers.removeIf(x -> !x.getCharge().toLowerCase().equals(charge.toLowerCase()));
		return dlSellers;
	}

	public Seller findById(String id) {
		return this.repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("There isn't a seller using this id"));
	}

	public Seller insert(Seller seller) {
		return this.repository.save(seller);
	}

	public Seller update(Seller obj) {
		Seller newObj = new Seller();
		this.updateSellerData(obj, newObj);
		return this.repository.save(newObj);
	}

	public void deleteById(String id) {
		this.findById(id);
		this.repository.deleteById(id);
	}

	public Seller fromSellerDto(SellerDto objDto) {
		Seller obj = this.findById(objDto.getId());
		return obj;
	}

	public void updateSellerData(Seller obj, Seller newObj) {
		newObj.setId(obj.getId());
		newObj.setName(obj.getName());
		newObj.setCharge(obj.getCharge());
		newObj.setEmail(obj.getEmail());
		newObj.setSalary(obj.getSalary());
		newObj.setDealership(obj.getDealership());
	}

}
