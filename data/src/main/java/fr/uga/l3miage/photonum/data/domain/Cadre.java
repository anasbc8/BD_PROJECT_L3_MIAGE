package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@DiscriminatorValue("4")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cadre extends Impression {

    @OneToMany(mappedBy = "cadrePhoto")
    private Set<Photo> photos;


}
