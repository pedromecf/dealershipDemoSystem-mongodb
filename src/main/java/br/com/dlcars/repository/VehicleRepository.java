package br.com.dlcars.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.dlcars.model.Vehicle;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {

}
