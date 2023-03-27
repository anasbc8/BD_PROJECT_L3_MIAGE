package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Calendrier;
import fr.uga.l3miage.photonum.service.base.BaseService;

import java.util.Collection;
import java.util.List;

public interface CalendrierService extends BaseService<Calendrier , String> {
     Calendrier save(Calendrier calendrier);
     void delete(String id) throws EntityNotFoundException ;
     Calendrier update(Calendrier calendrier) throws EntityNotFoundException;
}
