package ua.com.petfood.pf.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pf_animal_categories")
public class AnimalCategory extends PersistentEntity<Long> {

    private String name;

    public AnimalCategory() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
