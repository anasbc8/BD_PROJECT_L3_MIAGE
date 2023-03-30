package fr.uga.l3miage.photonum.data.domain;

import fr.uga.l3miage.photonum.data.domain.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Commande {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Basic(optional = false)
    private Date createdate;

    @Basic(optional = false)
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany
    private Set<Article> articles;

    @ManyToOne
    private Client client;


}
