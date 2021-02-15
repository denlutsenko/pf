package ua.com.petfood.pf.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pf_food_types")
public class FoodType extends PersistentEntity<Long> {

    private String type;
    private int code;

    public FoodType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
