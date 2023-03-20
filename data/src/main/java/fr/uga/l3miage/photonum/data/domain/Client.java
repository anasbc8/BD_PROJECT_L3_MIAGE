package fr.uga.l3miage.photonum.data.domain;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Client")
public class Client  {
@Id
@GeneratedValue
    private Long id ;
    private String email;
    private String firstName;
    private String lastName ;

    private String password;
    @OneToMany
    private Set<Photo> ownedImages;
    @ManyToMany
    private Set<Adress> adresses;

    public Set<Impression> getImpressions() {
        return impressions;
    }

    public void setImpressions(Set<Impression> impressions) {
        this.impressions = impressions;
    }

    @OneToMany
    private Set<Impression> impressions ;

    public Set<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Set<Commande> commandes) {
        this.commandes = commandes;
    }

    @OneToMany
    private Set<Commande> commandes ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Photo> getOwnedImages() {
        return ownedImages;
    }

    public void setOwnedImages(Set<Photo> ownedImages) {
        this.ownedImages = ownedImages;
    }

    public Set<Adress> getAdresses() {
        return adresses;
    }

    public void setAdresses(Set<Adress> adresses) {
        this.adresses = adresses;
    }
}
