package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Objects;
import java.util.Set;

@Entity
@DiscriminatorValue("3")
public class Tirage extends Impression {

    @OneToMany
    private Set<Photo> photos;

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tirage tirage = (Tirage) o;
        return Objects.equals(photos, tirage.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), photos);
    }

}
