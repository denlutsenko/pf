package ua.com.petfood.pf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.petfood.pf.model.DailyFoodAmount;

public interface DailyFoodAmountRepository extends JpaRepository<DailyFoodAmount, Long> {

    @Query(value = "SELECT food_amount FROM pf_daily_food_amount \n" +
            "WHERE pet_category = ? \n" +
            "AND adult_pet_size = ? \n" +
            "AND animal_age_type = ? \n" +
            "AND food_type = ? LIMIT 1", nativeQuery = true)
    Integer findDailyFoodAmountForDog(String petCategory, String adultPetSize, String animalAgeType, String foodType);

    @Query(value = "SELECT food_amount FROM pf_daily_food_amount \n" +
            "WHERE pet_category = ? \n" +
            "AND animal_age_type = ? \n" +
            "AND food_type = ? LIMIT 1", nativeQuery = true)
    Integer findDailyFoodAmountForCat(String petCategory, String animalAgeType, String foodType);
}


