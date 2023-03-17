package fr.uga.l3miage.photonum.data.domain;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Client")
@DiscriminatorValue("1")
public class Client extends Person {

    private String email;
    private String password;
    @OneToMany
    private Set<Photo> ownedImages;
    @ManyToMany
    private Set<Adress> adresses;

    public Set<Photo> getOwnedImages() {
        return this.ownedImages;
    }

    public void setOwnedImages(Set<Photo> ownedImages) {
        this.ownedImages = ownedImages;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Adress> getAdresses() {
        return adresses;
    }

    public void setAdresses(Set<Adress> adresses) {
        this.adresses = adresses;
    }

}
