package ua.com.petfood.pf.model;

public enum AdultPetSize {
    XS ("1.00"),
    S ("0 - 2.00"),
    M ("2.01 - 10.00"),
    XL ("10.01 - 32.00");

    public final String label;

    AdultPetSize(String label) {
        this.label = label;
    }
}
