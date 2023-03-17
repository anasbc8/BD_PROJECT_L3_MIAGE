package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@DiscriminatorValue("1")
public class Photo extends Image {
    private String retouching;
    @ManyToOne
    private Client owner;
    @ManyToOne
    private Page page;
    @ManyToMany(mappedBy = "photoCollection")
    private Set<Impression> impressions;

    @OneToMany
    private Set<Album> albums;

    public Photo() {

    }

    public Client getOwner() {
        return this.owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public String getRetouching() {
        return retouching;
    }

    public void setRetouching(String retouching) {
        this.retouching = retouching;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Set<Impression> getImpressions() {
        return impressions;
    }

    public void setImpressions(Set<Impression> impressions) {
        this.impressions = impressions;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Photo ph = (Photo) o;
        return this.owner == ph.owner && this.retouching == ph.retouching;
    }

    @Override
    public int HashCode() {
        return Objects.hash(this.owner, this.retouching);
    }
}
