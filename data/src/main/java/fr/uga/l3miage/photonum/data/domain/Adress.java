package fr.uga.l3miage.photonum.data.domain;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "*Address")
public class Adress {

    private String name;
    @ManyToMany(mappedBy = "adresses")
    private Set<Person> habitants;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getHabitants() {
        return this.habitants;
    }

    public void setHabitants(Set<Person> habitants) {
        this.habitants = habitants;
    }

}