package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Cadre;
import fr.uga.l3miage.photonum.data.domain.Tirage;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import fr.uga.l3miage.photonum.service.base.BaseService;

public interface CadreService  extends BaseService<Cadre,String> {
    public Cadre save(Cadre cadre);
    public Cadre update(Cadre cadre) throws EntityNotFoundException;
    public void delete(String id);

}
