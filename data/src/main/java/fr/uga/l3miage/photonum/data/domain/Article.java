package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;

@Entity
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    @Basic(optional = false)
    private int quantity;
    @Basic(optional = false)
    private Double prixTotal;

    @ManyToOne
    private Commande commande;

    @OneToOne
    private Impression impression;

    public Article(Long id, int quantity, Double prixTotal, Commande commande, Impression impression) {
        this.id = id;
        this.quantity = quantity;
        this.prixTotal = prixTotal;
        this.commande = commande;
        this.impression = impression;
    }

    public Article() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Impression getImpression() {
        return impression;
    }

    public void setImpression(Impression impression) {
        this.impression = impression;
    }
}
