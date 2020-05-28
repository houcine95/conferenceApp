package com.fennec.gestionconference.batch;

import com.fennec.gestionconference.entities.Abonne;
import org.springframework.batch.item.ItemProcessor;

public class AbonneItemProcessor implements ItemProcessor<Abonne, Abonne> {
    @Override
    public Abonne process(Abonne abonne) throws Exception {
        Abonne processAbonne = new Abonne();
        processAbonne.setNom(abonne.getNom());
        processAbonne.setPrenom(abonne.getPrenom());
        processAbonne.setSexe(abonne.getSexe());
        processAbonne.setAdresse(abonne.getAdresse());
        processAbonne.setTele(abonne.getTele());
        processAbonne.setEmail(abonne.getEmail());
        return processAbonne;
    }
}
