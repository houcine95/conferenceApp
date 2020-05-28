package com.fennec.gestionconference.dao;

import com.fennec.gestionconference.entities.CarteAbonnement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarteAbonnementRepository extends MongoRepository<CarteAbonnement, String> {
}
