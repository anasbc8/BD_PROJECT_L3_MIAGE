package fr.uga.l3miage.photonum.data.domain;

public enum Catalogue {

    Tirage_10x15MAT("10x15MAT", "Tirage", 0.50),
    Tirage_10x13BRIL("10x13BRIL", "Tirage", 0.40),
    Calendrier_CALA4BRIL("CALA4BRIL", "Calendrier", 2.50),
    Cadre_CADA3MAT("CADA3MAT", "Cadre", 1.50),
    Album_AL21x29MAT("ALBA3MAT", "Album", 5.99);

    private final String reference;
    private final String type;
    private final double price;

    Catalogue(String reference, String type, double price) {
        this.reference = reference;
        this.type = type;
        this.price = price;
    }

    public String getReference() {
        return reference;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}
