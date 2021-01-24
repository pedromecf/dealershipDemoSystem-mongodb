package br.com.dlcars.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.dlcars.model.Client;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

}
