package com.fennec.gestionconference.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.io.Serializable;
import java.util.Collection;

//@Entity
@Document
@Data @AllArgsConstructor @NoArgsConstructor
public class Abonne implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nom;
    private String prenom;
    private String sexe;
    private String adresse;
    private String tele;
    private String email;
    //private String typeAbonnement;

    //@OneToMany(mappedBy = "abonne")
    //private Collection<CarteAbonnement> carteAbonnements;
}
