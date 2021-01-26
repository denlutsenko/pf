package ua.com.petfood.pf.model;

public enum AdultPetSize {
    DEFAULT_VALUE ("1.00"),
    SMALL ("0 - 2.00"),
    MIDDLE ("2.01 - 10.00"),
    BIG ("10.01 - 32.00"),
    HUGE ("32.01 - 99.99");

    public final String label;

    AdultPetSize(String label) {
        this.label = label;
    }
}
