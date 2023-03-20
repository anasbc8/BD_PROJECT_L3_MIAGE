package fr.uga.l3miage.photonum.data.domain;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Page")
public class Page {

    private String text;

    @OneToMany
    private Set<Photo> photos;
    @Id
    @GeneratedValue
    private Long id;

    public Page(String text, Set<Photo> photos) {
        this.text = text;
        this.photos = photos;
    }

    public Page() {


    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
