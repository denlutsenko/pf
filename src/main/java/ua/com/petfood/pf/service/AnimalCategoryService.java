package ua.com.petfood.pf.service;

import java.util.List;

import ua.com.petfood.pf.model.AnimalCategory;

public interface AnimalCategoryService {

    List<AnimalCategory> getAnimalCategories();

    AnimalCategory getAnimalCategoryById(long id);
}
