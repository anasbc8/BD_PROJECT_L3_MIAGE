package fr.uga.l3miage.photonum.data.domain;
import jakarta.persistence.*;


public abstract class Image {

    private String path;
    private String owner;
    private String metadata;
    private double resolution;
    private boolean isShared;

    public String getPath() {
        return path;
    }

    public String getOwner() {
        return owner;
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

    public void setOwner(String owner) {
        this.owner = owner;
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
