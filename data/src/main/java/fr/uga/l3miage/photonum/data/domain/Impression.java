package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
public class Impression {

    @Id
    @GeneratedValue
    private Long id; // remplacer car String si besoin
}
