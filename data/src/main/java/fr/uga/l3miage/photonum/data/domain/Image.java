package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="image_type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Image {

    @Id
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    private String path;
    private String metadata;
    private double resolution;
    private boolean isShared;

    public Image(String path, String metadata, double resolution, boolean isShared) {
        this.path = path;
        this.metadata = metadata;
        this.resolution = resolution;
        this.isShared = isShared;
    }

    public Image() {

    }


    public String getPath() {
        return path;
    }

    public String getMetadata() {
        return metadata;
    }

    public double getResolution() {
        return resolution;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public void setResolution(double resolution) {
        this.resolution = resolution;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }

    public boolean isShared() {
        return isShared;
    }

    public abstract boolean equals(Object o);

    public abstract int HashCode();
}
