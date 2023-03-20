package fr.uga.l3miage.photonum.data.domain;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("1")
public class Photo{

    private String retouching;
    @OneToOne
    private Image image;
    @Id
    @GeneratedValue
    private Long id;

    public Photo(String retouching, Image image) {
        this.retouching = retouching;
        this.image = image;
    }

    public Photo() {

    }

    public Image getImage() {
        return this.image;
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


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
