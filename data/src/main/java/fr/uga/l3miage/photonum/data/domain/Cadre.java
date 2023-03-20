package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "Cadre")
@DiscriminatorValue("4")
public class Cadre extends Impression {

    @OneToMany
    private Set<Photo> photos;


}
