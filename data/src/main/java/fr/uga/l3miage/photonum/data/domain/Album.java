package fr.uga.l3miage.photonum.data.domain;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Album")
@DiscriminatorValue("2")
public class Album extends Impression {
    private Photo coverPhoto;


    @Basic(optional = false)

    private String title;
    @OneToMany
    private Set<Page> pages;

    public Photo getCoverPhoto() {
        return this.coverPhoto;
    }

    public void setCoverPhoto(Photo coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Page> getPages() {
        return this.pages;
    }

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }

}
