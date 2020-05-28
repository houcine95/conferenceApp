package com.fennec.gestionconference.dao;

import com.fennec.gestionconference.entities.Abonne;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AbonneRepository extends MongoRepository<Abonne, String> {
}
