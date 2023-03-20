package fr.uga.l3miage.photonum.data.domain;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Address")
public class Adress {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}