package com.fennec.gestionconference.batch;

import com.fennec.gestionconference.dao.AbonneRepository;
import com.fennec.gestionconference.entities.Abonne;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AbonneItemWriter implements ItemWriter<Abonne> {

    @Autowired
    private AbonneRepository abonneRepository;

    @Override
    public void write(List<? extends Abonne> list) throws Exception {
        abonneRepository.saveAll(list);
    }
}
