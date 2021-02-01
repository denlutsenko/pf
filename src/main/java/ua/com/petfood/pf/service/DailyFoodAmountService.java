package ua.com.petfood.pf.service;

public interface DailyFoodAmountService {
    int getDailyFoodAmountForDog(String petCategory, String adultAnimalSize, String animalAgeType, String foodType);
    int getDailyFoodAmountForCat();

}
