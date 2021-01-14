package ua.com.petfood.pf.model;

public enum AdultPetSize {
    SMALL ("0 - 5"),
    MIDDLE ("6 - 10"),
    BIG ("11 - 15"),
    HUGE ("16 - 20");

    public final String label;

    AdultPetSize(String label) {
        this.label = label;
    }
}
