package ua.com.petfood.pf.model;

public enum AdultPetSize {
    YOUNG_DOG("0-1"),
    ADULT_DOG("1-6"),
    OLD_DOG("6-20");

    public final String label;

    AdultPetSize(String label) {
        this.label = label;
    }

    private String getLabel() {
        return label;
    }
}
