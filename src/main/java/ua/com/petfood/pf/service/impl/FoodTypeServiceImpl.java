package ua.com.petfood.pf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.model.FoodType;
import ua.com.petfood.pf.repository.FoodTypeRepository;
import ua.com.petfood.pf.service.FoodTypeService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FoodTypeServiceImpl implements FoodTypeService {

    private FoodTypeRepository foodTypeRepository;

    @Autowired
    public FoodTypeServiceImpl(FoodTypeRepository foodTypeRepository) {
        this.foodTypeRepository = foodTypeRepository;
    }

    @Override
    public List<FoodType> getFoodTypes() {
        return Optional.of(foodTypeRepository.findAll()).orElseGet(Collections::emptyList);
    }
}
