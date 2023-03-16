
package fr.uga.l3miage.photonum.data.domain;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "Impression")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Impression {

    @Id
    @GeneratedValue
    private Long id; // remplacer car String si besoin

    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "format", nullable = false)
    private String format;
    @Column(name = "quality", nullable = false)
    private String quality;

    public String quality() {
        return this.quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /*
     * @Override
     * public boolean equals(Object o) {
     * if (this == o)
     * return true;
     * if (o == null || getClass() != o.getClass())
     * return false;
     * Impression impression = (Impression) o;
     * return Objects.equals(fullName, author.fullName);
     * }
     */

    /*
     * @Override
     * public int hashCode() {
     * return Objects.hash(fullName);
     * }
     */

}
