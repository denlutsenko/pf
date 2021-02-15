package ua.com.petfood.pf.service;

public interface DailyFoodAmountService {
    Integer getDailyFoodAmountForDog(String petCategory, String adultAnimalSize, String animalAgeType, String foodType);

    Integer getDailyFoodAmountForCat(String petCategory, String animalAgeType, String foodType);

}
