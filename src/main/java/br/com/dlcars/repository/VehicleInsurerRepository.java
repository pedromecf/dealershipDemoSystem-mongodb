package br.com.dlcars.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.dlcars.model.VehicleInsurer;

@Repository
public interface VehicleInsurerRepository extends MongoRepository<VehicleInsurer, String> {

}
