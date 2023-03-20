package fr.uga.l3miage.photonum.data.domain;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Calendrier")
@DiscriminatorValue("1")
public class Calendrier extends Impression {

    @OneToMany
    private Set<Page> pages;

    public Set<Page> getPages() {
        return this.pages;
    }

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Calendrier that = (Calendrier) o;
        return Objects.equals(pages, that.pages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pages);
    }
}
