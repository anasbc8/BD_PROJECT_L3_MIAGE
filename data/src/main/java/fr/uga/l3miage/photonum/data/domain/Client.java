package fr.uga.l3miage.photonum.data.domain;

import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Client extends Person {

    private Long id;
    private String email;
    private String password;
    private Set<Photo> ownedImages;

    public Set<Photo> getOwnedImages() {
        return this.ownedImages;
    }

    public void setOwnedImages(Set<Photo> ownedImages) {
        this.ownedImages = ownedImages;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
