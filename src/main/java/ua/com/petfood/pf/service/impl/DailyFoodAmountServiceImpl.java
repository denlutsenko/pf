package ua.com.petfood.pf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.petfood.pf.repository.DailyFoodAmountRepository;
import ua.com.petfood.pf.service.DailyFoodAmountService;

@Service
public class DailyFoodAmountServiceImpl implements DailyFoodAmountService {

    private DailyFoodAmountRepository dailyFoodAmountRepository;

    @Autowired
    public DailyFoodAmountServiceImpl(final DailyFoodAmountRepository dailyFoodAmountRepository) {
        this.dailyFoodAmountRepository = dailyFoodAmountRepository;
    }

    @Override
    public Integer getDailyFoodAmountForDog(final String petCategory, final String adultPetSize,
            final String animalAgeType, final String foodType) {

        return dailyFoodAmountRepository.findDailyFoodAmountForDog(petCategory, adultPetSize, animalAgeType, foodType);
    }

    @Override
    public Integer getDailyFoodAmountForCat(final String petCategory, final String animalAgeType,
            final String foodType) {

        return dailyFoodAmountRepository.findDailyFoodAmountForCat(petCategory, animalAgeType, foodType);
    }
}
