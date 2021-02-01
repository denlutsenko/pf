package ua.com.petfood.pf.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pf_daily_food_amount")
public class DailyFoodAmount extends PersistentEntity<Long> {

    private String petCategory;
    private String adultPetSize;
    private String animalAgeType;
    private String foodType;
    private int foodAmount;


    public DailyFoodAmount() {
    }


    public String getPetCategory() {
        return petCategory;
    }

    public void setPetCategory(String petCategory) {
        this.petCategory = petCategory;
    }

    public String getAdultPetSize() {
        return adultPetSize;
    }

    public void setAdultPetSize(String adultPetSize) {
        this.adultPetSize = adultPetSize;
    }

    public String getAnimalAgeType() {
        return animalAgeType;
    }

    public void setAnimalAgeType(String animalAgeType) {
        this.animalAgeType = animalAgeType;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }
}
