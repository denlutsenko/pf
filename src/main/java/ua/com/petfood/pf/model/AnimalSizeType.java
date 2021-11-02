package ua.com.petfood.pf.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Table(name = "pf_animal_sizes")
@Entity
@Getter
@Setter
public class AnimalSizeType extends PersistentEntity<Long> {

    private String shortName;
    private String longName;

    @ManyToOne
    @JsonBackReference
    private SKUItem skuItemForDog;

    public AnimalSizeType() {
    }
}
