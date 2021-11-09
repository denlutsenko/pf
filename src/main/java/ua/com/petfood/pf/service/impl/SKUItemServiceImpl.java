package ua.com.petfood.pf.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.SKUPrice;
import ua.com.petfood.pf.model.dto.OrderSKUItems;
import ua.com.petfood.pf.repository.SKUItemRepository;
import ua.com.petfood.pf.service.SKUItemService;
import ua.com.petfood.pf.service.SKUPriceService;

@Service
public class SKUItemServiceImpl implements SKUItemService {

    private final SKUItemRepository skuItemRepository;
    private final SKUPriceService skuPriceService;

    @Autowired
    public SKUItemServiceImpl(SKUItemRepository skuItemRepository, SKUPriceService skuPriceService) {
        this.skuItemRepository = skuItemRepository;
        this.skuPriceService = skuPriceService;
    }

    @Override
    public SKUItem findRecommendedSKUItemForDog(final Long animalCategoryId, final String brand,
            final String dogAdultType, final Long foodTypeId, final String animalSize, final double skuWeightKilos,
            final boolean bestseller) {

        return skuItemRepository.getRecommendedSKUItemForDog(animalCategoryId, brand, dogAdultType, foodTypeId,
                animalSize, bestseller, skuWeightKilos);
    }

    @Override
    public SKUItem findRecommendedSKUItemForCat(final Long animalCategoryId, final String brand,
            final String dogAdultType, final Long foodTypeId, final double skuWeightKilos, final boolean bestseller) {

        return skuItemRepository.getRecommendedSKUItemForCat(animalCategoryId, brand, dogAdultType, foodTypeId,
                bestseller, skuWeightKilos);
    }

    @Override
    public SKUItem findRecommendedSKUItemForOthers(Long animalCategoryId, String brand, double skuWeightKilos,
            boolean bestseller) {
        return skuItemRepository.getRecommendedSKUItemForOthers(animalCategoryId, brand, skuWeightKilos, bestseller);
    }

    @Override
    public Double findClosestSKUWeightForDog(final String brand, final double weight, final String animalAgeType,
            final Long preferableFoodId, final String adultDogSize, final Long animalCategoryId) {

        Double closestAndLargestSkuWeight = skuItemRepository.findClosestAndLargestSkuWeight(brand, weight,
                animalAgeType, preferableFoodId, adultDogSize, animalCategoryId);
        Double closestAndLesserSkuWeight = skuItemRepository.findClosestAndLesserSkuWeightForDog(brand, weight,
                animalAgeType, preferableFoodId, adultDogSize, animalCategoryId);

        return getLargestOrLesserValue(closestAndLargestSkuWeight, closestAndLesserSkuWeight);
    }

    @Override
    public Double findClosestSKUWeightForCat(final String brand, final double weight, final String animalAgeType,
            final Long preferableFoodId, Long animalCategoryId) {
        Double closestAndLargestSkuWeight = skuItemRepository.findClosestAndLargestSkuWeightForCat(brand, weight,
                animalAgeType, preferableFoodId, animalCategoryId);
        Double closestAndLesserSkuWeight = skuItemRepository.findClosestAndLesserSkuWeightForCat(brand, weight,
                animalAgeType, preferableFoodId, animalCategoryId);

        return getLargestOrLesserValue(closestAndLargestSkuWeight, closestAndLesserSkuWeight);
    }

    @Override
    public Double findClosestSKUWeightForOthers(final Long animalCategoryId, final double severalDaysFoodAmountKilos,
            final String brand) {
        Double closestAndLargestSkuWeight = skuItemRepository.findClosestAndLargestSkuWeightForOthers(animalCategoryId,
                severalDaysFoodAmountKilos, brand);
        Double closestAndLesserSkuWeight = skuItemRepository.findClosestAndLesserSkuWeightForOthers(animalCategoryId,
                severalDaysFoodAmountKilos, brand);

        return getLargestOrLesserValue(closestAndLargestSkuWeight, closestAndLesserSkuWeight);

    }

    private Double getLargestOrLesserValue(Double closestAndLargestSkuWeight, Double closestAndLesserSkuWeight) {
        if(closestAndLargestSkuWeight != null) {
            return closestAndLargestSkuWeight;
        } else if(closestAndLesserSkuWeight != null) {
            return closestAndLesserSkuWeight;
        } else {
            return 0.00;
        }
    }

    @Override
    public List<String> getSkuBrandsByPetCategory(Long petCategoryId) {

        return skuItemRepository.findSKUBrandsByPetCategory(petCategoryId);
    }

    @Override
    public List<SKUItem> getSKUItemsByAnimalCategoryId(Long animalCategoryId) {
        return skuItemRepository.findSKULineItemsByAnimalCategoryId(animalCategoryId);
    }

    @Override
    public List<SKUPrice> getSKUItemsWithPricesByAnimalCategory(Long animalCategoryId) {
        return skuPriceService.findSKUItemsWithPricesByAnimalCategory(animalCategoryId);
    }

    @Override
    public SKUItem findSKUItemById(Long id) {
        return skuItemRepository.findById(id).orElseThrow(() -> new NotFoundException("SKU Item not found"));
    }

    @Override
    public List<SKUItem> getSkuItemListFromOrderSKUItemDTOs(List<OrderSKUItems> orderSKUItemDTOList) {
        return orderSKUItemDTOList.stream().map(OrderSKUItems::getSkuItemId).map(this::findSKUItemById)
                .collect(Collectors.toList());
    }
}
