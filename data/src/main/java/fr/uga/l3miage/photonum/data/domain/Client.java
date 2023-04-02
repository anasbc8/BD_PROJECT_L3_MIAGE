package fr.uga.l3miage.photonum.data.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @OneToMany(mappedBy = "owner",fetch = FetchType.EAGER)
    private Set<Image> ownedImages;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Adress> adresses;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Impression> impressions;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "client")
    @JsonIgnore
    private Set<Commande> commandes;

    public void addImage(Image image) {
        ownedImages.add(image);
    }

}
