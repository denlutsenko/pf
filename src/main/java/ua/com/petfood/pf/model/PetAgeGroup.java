package ua.com.petfood.pf.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pf_pet_age_group")
public class PetAgeGroup extends PersistentEntity<Long> {

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private Group groupName;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private AdultPetSize ageRange;

    @Transient
    private String petAgeRange;

    @ManyToOne
    @JsonBackReference
    private SKUItem sku;

    public PetAgeGroup() {
    }

    @PostLoad
     void fillTransientPetAgeRange() {
        setPetAgeRange(ageRange.label);
    }

    public Group getGroupName() {
        return groupName;
    }

    public void setGroupName(final Group groupName) {
        this.groupName = groupName;
    }

    public AdultPetSize getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(final AdultPetSize ageRange) {
        this.ageRange = ageRange;
    }

    public SKUItem getSku() {
        return sku;
    }

    public void setSku(final SKUItem sku) {
        this.sku = sku;
    }

    public String getPetAgeRange() {
        return petAgeRange;
    }

    public void setPetAgeRange(final String petAgeRange) {
        this.petAgeRange = petAgeRange;
    }
}
