package fr.uga.l3miage.photonum.data.domain;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Page")
public class Page {
    @Id
    @GeneratedValue
    private Long id;
    private Set<Photo> photos;
    private String text;
    @ManyToOne
    private Album album;
    @ManyToOne
    private Calendrier calendrier;


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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Calendrier getCalendrier() {
        return calendrier;
    }

    public void setCalendrier(Calendrier calendrier) {
        this.calendrier = calendrier;
    }
}
