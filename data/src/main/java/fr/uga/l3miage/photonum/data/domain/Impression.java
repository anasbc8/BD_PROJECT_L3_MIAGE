
package fr.uga.l3miage.photonum.data.domain;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Impression")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "impression_type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Impression {

    @Id
    @GeneratedValue
    private Long id; // remplacer car String si besoin

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToMany
    private Set<Photo> photoCollection;

    @OneToOne
    private Article article;

    @Enumerated
    private Catalogue reference;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Photo> getPhotoCollection() {
        return photoCollection;
    }

    public void setPhotoCollection(Set<Photo> photoCollection) {
        this.photoCollection = photoCollection;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Catalogue getReference() {
        return reference;
    }

    public void setReference(Catalogue reference) {
        this.reference = reference;
    }

    /*
     * @Override
     * public boolean equals(Object o) {
     * if (this == o)
     * return true;
     * if (o == null || getClass() != o.getClass())
     * return false;
     * Impression impression = (Impression) o;
     * return Objects.equals(fullName, author.fullName);
     * }
     */

    /*
     * @Override
     * public int hashCode() {
     * return Objects.hash(fullName);
     * }
     */

}
