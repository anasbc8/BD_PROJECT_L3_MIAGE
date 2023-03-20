package fr.uga.l3miage.photonum.data.domain;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Album")
@DiscriminatorValue("2")
public class Album extends Impression {

    private String title;
    @OneToOne
    private Photo photoCover;
    @OneToMany
    private Set<Page> pages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Photo getPhotoCover() {
        return photoCover;
    }

    public void setPhotoCover(Photo photoCover) {
        this.photoCover = photoCover;
    }

    public Set<Page> getPages() {
        return pages;
    }

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Album album = (Album) o;
        return Objects.equals(title, album.title) && Objects.equals(photoCover, album.photoCover) && Objects.equals(pages, album.pages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, photoCover, pages);
    }

}
