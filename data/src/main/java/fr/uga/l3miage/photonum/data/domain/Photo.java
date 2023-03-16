package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Photo extends Image {
    private Image image;
    private String retouching;
    @ManyToOne
    private Client owner;

    public Client getOwner() {
        return this.owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getRetouching() {
        return retouching;
    }

    public void setRetouching(String retouching) {
        this.retouching = retouching;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Photo ph = (Photo) o;
        return this.image == ph.image && this.retouching == ph.retouching;
    }

    @Override
    public int HashCode() {
        return Objects.hash(this.image, this.retouching);
    }
}
