package ua.com.petfood.pf.service;

import java.util.List;

import ua.com.petfood.pf.model.FoodType;

public interface FoodTypeService {

    List<FoodType> getFoodTypes();

    String getFoodTypeByCode(int id);
}
