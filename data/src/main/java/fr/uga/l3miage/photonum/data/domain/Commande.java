package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.Set;

@Entity
public class Commande {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private String id;

    @Basic(optional = false)
    private Date date;

    @Basic(optional = false)
    private Double totalPrice;

    @OneToMany
    private Set<Article> articles;

    public Commande(String id, Date date, Double totalPrice, Set<Article> articles) {
        this.id = id;
        this.date = date;
        this.totalPrice = totalPrice;
        this.articles = articles;
    }

    public Commande() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> article) {
        this.articles = article;
    }
}
