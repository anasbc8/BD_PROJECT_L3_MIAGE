package fr.uga.l3miage.photonum.data.domain;

import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Page")
public class Page {
    private Set<Photo> photos;
    private String text;

    public Set<Photo> getPhotos() {
        return this.photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
