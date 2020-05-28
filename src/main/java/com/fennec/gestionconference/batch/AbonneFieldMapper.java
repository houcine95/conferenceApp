package com.fennec.gestionconference.batch;

import com.fennec.gestionconference.entities.Abonne;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class AbonneFieldMapper implements FieldSetMapper<Abonne> {
    @Override
    public Abonne mapFieldSet(FieldSet fieldSet) throws BindException {
        Abonne abonne = new Abonne();
        abonne.setNom(fieldSet.readString("nom"));
        abonne.setPrenom(fieldSet.readString("prenom"));
        abonne.setSexe(fieldSet.readString("sexe"));
        abonne.setAdresse(fieldSet.readString("adresse"));
        abonne.setTele(fieldSet.readString("tele"));
        abonne.setEmail(fieldSet.readString("email"));
        return abonne;
    }
}
