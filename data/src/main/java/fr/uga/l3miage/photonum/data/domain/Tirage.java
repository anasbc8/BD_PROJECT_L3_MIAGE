package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("3")
public class Tirage extends Impression {

}
