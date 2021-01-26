package ua.com.petfood.pf.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pf_food_types")
public class FoodType extends PersistentEntity<Long> {

    private String type;

    public FoodType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
