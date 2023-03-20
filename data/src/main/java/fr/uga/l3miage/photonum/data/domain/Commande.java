package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Commande {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Basic(optional = false)
    private Date createdAt;

    @Basic(optional = false)
    private Double totalPrice;

    @OneToMany(mappedBy = "commande")
    private Set<Article> articles;

    @ManyToOne
    private Client client;


}
