package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cadre")
@DiscriminatorValue("4")
public class Cadre extends Impression {

}
