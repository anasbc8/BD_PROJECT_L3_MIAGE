package fr.uga.l3miage.photonum.data.domain;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "deliveryAddress")
public class DeliveryAddress {
    private String address;
    private String name;
    @ManyToMany(mappedBy = "adresses")
    private Set<Person> habitants;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
