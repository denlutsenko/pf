package ua.com.petfood.pf.service;

public interface DailyFoodAmountService {
    Integer getDailyFoodAmountForDog(String petCategory, String adultAnimalSize, String animalAgeType, String foodType);
    int getDailyFoodAmountForCat();

}
