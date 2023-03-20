package fr.uga.l3miage.photonum.data.domain;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Article")
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    @Basic(optional = false)
    private int quantity;
    @Basic(optional = false)
    private double prixTotal;
    @OneToOne
    private Impression impression;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Impression getImpression() {
        return impression;
    }

    public void setImpression(Impression impression) {
        this.impression = impression;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return quantity == article.quantity && Double.compare(article.prixTotal, prixTotal) == 0 && Objects.equals(id, article.id) && Objects.equals(impression, article.impression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, prixTotal, impression);
    }
}