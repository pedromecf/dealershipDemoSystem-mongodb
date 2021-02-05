package br.com.dlcars.service;

import java.util.List;

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
		return this.repository.findAll();
	}

	public VehicleInsurer findById(String id) {
		return this.repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("There isn't a insurer using this id"));
	}

	public VehicleInsurer insert(VehicleInsurer obj) {
		return this.repository.save(obj);
	}

	public VehicleInsurer update(VehicleInsurer obj) {
		VehicleInsurer newObj = this.findById(obj.getId());
		setInsurerData(obj, newObj);
		return this.repository.save(obj);
	}

	public void deleteById(String id) {
		this.findById(id);
		this.repository.deleteById(id);
	}

	public void setInsurerData(VehicleInsurer obj, VehicleInsurer newObj) {
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
