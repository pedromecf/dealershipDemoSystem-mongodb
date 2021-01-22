package br.com.dlcars.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.dlcars.model.VehicleInsurer;

public interface VehicleInsurerRepository extends MongoRepository<VehicleInsurer, String> {

}
