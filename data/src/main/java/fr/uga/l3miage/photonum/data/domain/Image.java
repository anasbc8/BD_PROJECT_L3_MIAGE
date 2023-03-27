package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public abstract class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Basic(optional = false)
    private String path;
    private String metadata;
    private double resolution;
    private boolean isShared;
    @ManyToOne
    private Client owner;

}
