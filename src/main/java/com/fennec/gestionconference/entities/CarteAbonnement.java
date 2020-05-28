package com.fennec.gestionconference.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

//@Entity
@Document
@Data @AllArgsConstructor @NoArgsConstructor
public class CarteAbonnement implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String numCarte;
    private String typeCarte;
    private Date dateExp;

    //@ManyToOne
    //@JoinColumn(name = "id_abonne")
    //private Abonne abonne;
}
