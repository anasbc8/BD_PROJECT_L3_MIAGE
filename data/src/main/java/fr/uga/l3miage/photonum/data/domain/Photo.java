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
    @ManyToMany(mappedBy = "photoCollection")
    private Set<Impression> impressions;

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

    public Set<Impression> getImpressions() {
        return impressions;
    }

    public void setImpressions(Set<Impression> impressions) {
        this.impressions = impressions;
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
