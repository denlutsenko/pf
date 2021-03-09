package ua.com.petfood.pf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.AnimalCategory;
import ua.com.petfood.pf.repository.AnimalCategoryRepository;
import ua.com.petfood.pf.service.AnimalCategoryService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalCategoryServiceServiceImpl implements AnimalCategoryService {

    private AnimalCategoryRepository animalCategoryRepository;

    @Autowired
    public AnimalCategoryServiceServiceImpl(AnimalCategoryRepository animalCategoryRepository) {
        this.animalCategoryRepository = animalCategoryRepository;
    }

    @Override
    public List<AnimalCategory> getAnimalCategories() {
        return Optional.of(animalCategoryRepository.findAll()).orElseGet(Collections::emptyList);
    }

    @Override
    public AnimalCategory getAnimalCategoryById(final long id) {
        AnimalCategory animalCategory = animalCategoryRepository.findById(id).orElse(null);

        return animalCategoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Animal not found"));
    }
}
