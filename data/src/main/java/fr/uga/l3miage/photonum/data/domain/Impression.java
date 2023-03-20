
package fr.uga.l3miage.photonum.data.domain;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Impression")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "impression_type", discriminatorType = DiscriminatorType.INTEGER)
public class Impression {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "price", nullable = false)
    private double price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Impression that = (Impression) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(id, that.id) && Objects.equals(article, that.article) && reference == that.reference;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, article, reference);
    }
}
