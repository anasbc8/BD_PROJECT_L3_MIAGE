package fr.uga.l3miage.photonum.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Basic(optional = false)
    private String path;
    private String metadata;
    private double resolution;
    private boolean isShared;
    @ManyToOne
    @JsonIgnore
    private Client owner;

}
