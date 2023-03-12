package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Impression;
import fr.uga.l3miage.photonum.service.base.BaseService;

import java.util.Collection;

public interface ImpressionService extends BaseService<Impression, Long> {

    Impression save(Impression impression);


}