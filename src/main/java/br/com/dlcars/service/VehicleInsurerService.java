package br.com.dlcars.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dlcars.model.VehicleInsurer;
import br.com.dlcars.model.dto.VehicleInsurerDto;
import br.com.dlcars.repository.VehicleInsurerRepository;
import br.com.dlcars.service.exception.ObjectNotFoundException;

@Service
public class VehicleInsurerService {

	@Autowired
	private VehicleInsurerRepository repository;
	
	public List<VehicleInsurer> findAll() {
		List<VehicleInsurer> insurers = this.repository.findAll();
		return insurers;
	}
	
	public VehicleInsurer findById(String id) {
		if (id.isBlank() || id.isEmpty()) {
			throw new IllegalArgumentException("The parameter can't be blank or empty");
		}
		Optional<VehicleInsurer> insurer = this.repository.findById(id);
		return insurer.orElseThrow(() -> new ObjectNotFoundException("There isn't a insurer using this id"));
	}
	
	public VehicleInsurer insert(VehicleInsurer obj) {
		return this.repository.save(obj);
	}
	
	public VehicleInsurer update(VehicleInsurer obj) {
		VehicleInsurer newObj = this.findById(obj.getId());
		updateInsurerData(obj, newObj);
		return this.repository.save(obj);
	}
	
	public void deleteById(String id) {
		if (id.isBlank() || id.isEmpty()) {
			throw new IllegalArgumentException("The parameter can't be blank or empty");
		}
		this.repository.deleteById(id);
	}
	
	public void updateInsurerData(VehicleInsurer obj, VehicleInsurer newObj) {
		newObj.setId(obj.getId());
		newObj.setName(obj.getName());
	}
	
	public VehicleInsurer fromInsurerDto(VehicleInsurerDto objDto) {
		VehicleInsurer obj = new VehicleInsurer();
		obj.setId(objDto.getId());
		obj.setName(objDto.getName());
		return obj;
	}
	
}
