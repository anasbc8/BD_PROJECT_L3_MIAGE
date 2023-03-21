package fr.uga.l3miage.photonum.data.domain;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("1")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Calendrier extends Impression {

    @OneToMany
    private Set<Page> pages;

}
