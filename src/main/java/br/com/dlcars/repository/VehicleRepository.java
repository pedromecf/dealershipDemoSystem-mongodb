package br.com.dlcars.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.dlcars.model.Vehicle;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
	
}
