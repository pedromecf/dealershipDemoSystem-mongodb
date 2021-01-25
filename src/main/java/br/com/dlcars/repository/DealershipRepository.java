package br.com.dlcars.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.dlcars.model.Dealership;

public interface DealershipRepository extends MongoRepository<Dealership, String> {

}
