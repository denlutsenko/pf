package ua.com.petfood.pf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.petfood.pf.model.SKUItem;

import java.util.List;

@Repository
public interface SKUItemRepository extends JpaRepository<SKUItem, Long> {

    //DOG
    @Query(value = "SELECT * FROM pf_sku_items \n" +
            "WHERE animal_category_id = ?1 \n" +
            "AND brand = ?2 \n" +
            "AND pet_group =  ?3 \n" +
            "AND food_type_id = ?4 \n" +
            "AND animal_size = ?5 \n" +
            "AND bestseller = ?6 \n" +
            "AND package_weight_kilos = ?7 LIMIT 1", nativeQuery = true)
    SKUItem getRecommendedSKUItemForDog(Long animalCategoryId, String brand, String petGroup, Long foodTypeId,
            String animalSize, boolean bestseller, double packageWeightKilos);


    //CAT
    @Query(value = "SELECT * FROM pf_sku_items \n" +
            "WHERE animal_category_id = ?1 \n" +
            "AND brand = ?2 \n" +
            "AND pet_group =  ?3 \n" +
            "AND food_type_id = ?4 \n" +
            "AND bestseller = ?5 \n" +
            "AND package_weight_kilos = ?6 LIMIT 1", nativeQuery = true)
    SKUItem getRecommendedSKUItemForCat(Long animalCategoryId, String brand, String petGroup, Long foodTypeId,
            boolean bestseller, double packageWeightKilos);

    //OTHERS
    @Query(value = "SELECT * FROM pf_sku_items \n" +
            "WHERE animal_category_id = ?1 \n" +
            "AND brand = ?2 \n" +
            "AND package_weight_kilos = ?3 \n" +
            "AND bestseller = ?4 LIMIT 1", nativeQuery = true)
    SKUItem getRecommendedSKUItemForOthers(Long animalCategoryId, String brand, double skuWeightKilos,
            boolean bestseller);


    //DOG Find the closest and LARGEST value similar to SKU weight in kilos in either direction:
    @Query(value = "SELECT package_weight_kilos FROM pf_sku_items " +
            "WHERE brand = ?1 " +
            "AND package_weight_kilos >= ?2 " +
            "AND pet_group = ?3 " +
            "AND food_type_id = ?4 " +
            "AND animal_size = ?5 " +
            "AND animal_category_id = ?6 " +
            "ORDER BY package_weight_kilos LIMIT 1", nativeQuery = true)
    Double findClosestAndLargestSkuWeight(String brand, double weight, String pet_group, Long food_type_id,
            String animal_size, Long animalCategoryId);


    //CAT Find the closest and LARGEST value similar to SKU weight in kilos in either direction:
    @Query(value = "SELECT package_weight_kilos FROM pf_sku_items " +
            "WHERE brand = ?1 " +
            "AND package_weight_kilos >= ?2 " +
            "AND pet_group = ?3 " +
            "AND food_type_id = ?4 " +
            "AND animal_category_id = ?5 " +
            "ORDER BY package_weight_kilos LIMIT 1", nativeQuery = true)
    Double findClosestAndLargestSkuWeightForCat(String brand, double weight, String pet_group, Long food_type_id,
            Long animalCategoryId);


    //OTHERS Find the closest and LARGEST value similar to SKU weight in kilos in either direction:
    @Query(value = "SELECT package_weight_kilos FROM pf_sku_items " +
            "animal_category_id =?1 " +
            "AND package_weight_kilos >= ?2 " +
            "AND brand = ?3 " +
            "ORDER BY package_weight_kilos LIMIT 1", nativeQuery = true)
    Double findClosestAndLargestSkuWeightForOthers(Long animalCategoryId, double severalDaysFoodAmountKilos,
            String brand);

    //OTHERS Find the closest and LESSER value similar to SKU weight in kilos in either direction:
    @Query(value = "SELECT package_weight_kilos FROM pf_sku_items " +
            "animal_category_id =?1 " +
            "AND package_weight_kilos <= ?2 " +
            "AND brand = ?3 " +
            "ORDER BY package_weight_kilos DESC LIMIT 1", nativeQuery = true)
    Double findClosestAndLesserSkuWeightForOthers(Long animalCategoryId, double severalDaysFoodAmountKilos,
            String brand);

    //DOG Find the closest and LESSER value similar to SKU weight in kilos in either direction:
    @Query(value = "SELECT package_weight_kilos FROM pf_sku_items " +
            "WHERE brand = ?1 " +
            "AND package_weight_kilos <= ?2 " +
            "AND pet_group = ?3 " +
            "AND food_type_id = ?4 " +
            "AND animal_size = ?5 " +
            "AND animal_category_id = ?6 " +
            "ORDER BY package_weight_kilos DESC LIMIT 1", nativeQuery = true)
    Double findClosestAndLesserSkuWeightForDog(String brand, double weight, String pet_group, Long food_type_id,
            String animal_size, Long animalCategoryId);

    //CAT Find the closest and LESSER value similar to SKU weight in kilos in either direction:
    @Query(value = "SELECT package_weight_kilos FROM pf_sku_items " +
            "WHERE brand = ?1 " +
            "AND package_weight_kilos <= ?2 " +
            "AND pet_group = ?3 " +
            "AND food_type_id = ?4 " +
            "AND animal_category_id = ?5 " +
            "ORDER BY package_weight_kilos DESC LIMIT 1", nativeQuery = true)
    Double findClosestAndLesserSkuWeightForCat(String brand, double weight, String pet_group, Long food_type_id,
            Long animalCategoryId);


    @Query(value = "SELECT DISTINCT(brand) FROM pf_sku_items WHERE animal_category_id = ?", nativeQuery = true)
    List<String> findSKUBrandsByPetCategory(Long petCategoryId);

    List<SKUItem> findSKULineItemsByAnimalCategoryId(Long animalCategoryId);
}
