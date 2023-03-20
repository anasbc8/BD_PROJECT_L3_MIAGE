package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Basic(optional = false)
    private int quantity;
    @Basic(optional = false)
    private double prixTotal;
    @OneToOne
    private Impression impression;

    @ManyToOne
    private Commande commande;

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