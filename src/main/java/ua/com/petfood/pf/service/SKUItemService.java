package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.SKUItem;

import java.util.List;

public interface SKUItemService {
    SKUItem findRecommendedSKUItemForDog(Long animalCategoryId, String brand, String dogAdultType, Long foodTypeId,
                                         String animalSize, double skuWeightKilos, boolean bestseller);

    Double findClosestSKUWeight(String brand, double weight, String animalAgeType, Long preferableFoodId,
                                String adultDogSize);

    List<String> getSkuBrandsByPetCategory(Long petCategoryId);
}
