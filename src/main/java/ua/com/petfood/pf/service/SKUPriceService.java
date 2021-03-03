package ua.com.petfood.pf.service;

import java.util.List;

import ua.com.petfood.pf.model.SKUPrice;

public interface SKUPriceService {

    List<SKUPrice> findSKUItemsWithPricesByAnimalCategory(Long animalCategoryId);
}
