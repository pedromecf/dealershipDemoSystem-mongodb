package br.com.dlcars.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.dlcars.model.Seller;

public interface SellerRepository extends MongoRepository<Seller, String> {

}
