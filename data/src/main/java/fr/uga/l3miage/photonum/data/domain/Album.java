package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Album")
@DiscriminatorValue("2")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album extends Impression {
    private String title;
    @OneToOne
    private Photo photoCover;
    @OneToMany
    private Set<Page> pages;

}
