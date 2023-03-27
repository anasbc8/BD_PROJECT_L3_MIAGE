package fr.uga.l3miage.photonum.data.domain;

import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;
    private String firstName;
    private String lastName;

    private String password;
    @OneToMany
    private Set<Photo> ownedImages;
    @ManyToMany
    private Set<Adress> adresses;

    @OneToMany
    private Set<Impression> impressions;
    @OneToMany(mappedBy = "client")
    private Set<Commande> commandes;

}
