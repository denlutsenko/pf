package ua.com.petfood.pf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pf_dog_sizes")
public class DogSize extends PersistentEntity<Long> {
    private String name;
    private String short_name;
    private double min_weight_kg;
    private double max_weight_kg;

    public DogSize() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public double getMin_weight_kg() {
        return min_weight_kg;
    }

    public void setMin_weight_kg(double min_weight_kg) {
        this.min_weight_kg = min_weight_kg;
    }

    public double getMax_weight_kg() {
        return max_weight_kg;
    }

    public void setMax_weight_kg(double max_weight_kg) {
        this.max_weight_kg = max_weight_kg;
    }
}
