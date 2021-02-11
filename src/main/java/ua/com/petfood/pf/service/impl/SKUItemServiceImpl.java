package ua.com.petfood.pf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.repository.SKUItemRepository;
import ua.com.petfood.pf.service.SKUItemService;

import java.util.List;

@Service
public class SKUItemServiceImpl implements SKUItemService {

    private SKUItemRepository skuItemRepository;

    @Autowired
    public SKUItemServiceImpl(SKUItemRepository skuItemRepository) {
        this.skuItemRepository = skuItemRepository;
    }

    @Override
    public SKUItem findRecommendedSKUItemForDog(final Long animalCategoryId, final String brand,
                                                final String dogAdultType, final Long foodTypeId,
                                                final String animalSize, final double skuWeightKilos,
                                                final boolean bestseller) {

        return skuItemRepository.getRecommendedSKUItemForDog(animalCategoryId, brand, dogAdultType, foodTypeId,
                animalSize, bestseller, skuWeightKilos);
    }

    @Override
    public Double findClosestSKUWeight(final String brand, final double weight, final String animalAgeType,
                                       final Long preferableFoodId, final String adultDogSize) {
        Double closestAndLargestSkuWeight = skuItemRepository.findClosestAndLargestSkuWeight(brand, weight,
                animalAgeType, preferableFoodId, adultDogSize);
        Double closestAndLesserSkuWeight = skuItemRepository.findClosestAndLesserSkuWeight(brand, weight, animalAgeType,
                preferableFoodId, adultDogSize);

        if (closestAndLargestSkuWeight != null) {
            return closestAndLargestSkuWeight;
        } else if (closestAndLesserSkuWeight != null) {
            return closestAndLesserSkuWeight;
        } else {
            throw new NotFoundException("SKU weight not found");
        }
    }

    @Override
    public List<String> getSkuBrandsByPetCategory(Long petCategoryId) {

        return skuItemRepository.findSKUBrandsByPetCategory(petCategoryId);
    }
}
