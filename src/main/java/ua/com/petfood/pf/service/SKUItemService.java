package ua.com.petfood.pf.service;

import java.util.List;

import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.SKUPrice;
import ua.com.petfood.pf.model.dto.OrderSKUItems;

public interface SKUItemService {

    SKUItem findRecommendedSKUItemForDog(Long animalCategoryId, String brand, String dogAdultType, Long foodTypeId,
            String animalSize, double skuWeightKilos, boolean bestseller);

    SKUItem findRecommendedSKUItemForCat(Long animalCategoryId, String brand, String dogAdultType, Long foodTypeId,
            double skuWeightKilos, boolean bestseller);

    SKUItem findRecommendedSKUItemForOthers(Long animalCategoryId, String brand, double skuWeightKilos,
            boolean bestseller);

    Double findClosestSKUWeightForDog(String brand, double weight, String animalAgeType, Long preferableFoodId,
            String adultDogSize, Long animalCategoryId);

    Double findClosestSKUWeightForCat(String brand, double weight, String animalAgeType, Long preferableFoodId,
            Long animalCategoryId);

    Double findClosestSKUWeightForOthers(Long animalCategoryId, double severalDaysFoodAmountKilos, String brand);

    List<String> getSkuBrandsByPetCategory(Long petCategoryId);

    List<SKUItem> getSKUItemsByAnimalCategoryId(Long animalCategoryId);

    List<SKUPrice> getSKUItemsWithPricesByAnimalCategory(Long animalCategoryId);

    SKUPrice getSkuItemDetailsWithPrice(Long skuItemId);

    SKUItem findSKUItemById(Long id);

    List<SKUItem> getSkuItemListFromOrderSKUItemDTOs(List<OrderSKUItems> orderSKUItemDTOList);
}
