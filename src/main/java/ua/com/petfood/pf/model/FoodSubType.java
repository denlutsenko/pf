package ua.com.petfood.pf.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pf_food_sub_types")
public class FoodSubType extends PersistentEntity<Long> {

    private String subType;

    public FoodSubType() {
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(final String subType) {
        this.subType = subType;
    }
}

