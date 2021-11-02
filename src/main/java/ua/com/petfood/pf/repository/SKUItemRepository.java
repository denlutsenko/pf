package ua.com.petfood.pf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.petfood.pf.model.SKUItem;

import java.util.List;

@Repository
public interface SKUItemRepository extends JpaRepository<SKUItem, Long> {

    //DOG
    @Query(value = "SELECT * "
            + "FROM pf_sku_items sku "
            + "INNER JOIN pf_pet_age_group gr ON gr.sku_id = sku.id " +
            "WHERE sku.animal_category_id = ?1 " +
            "AND sku.brand = ?2 " +
            "AND gr.group_name =  ?3 " +
            "AND sku.food_type_id = ?4 " +
            "AND sku.animal_size = ?5 " +
            "AND sku.bestseller = ?6 " +
            "AND sku.package_weight_kilos = ?7 LIMIT 1", nativeQuery = true)
    SKUItem getRecommendedSKUItemForDog(Long animalCategoryId, String brand, String petGroup, Long foodTypeId,
            String animalSize, boolean bestseller, double packageWeightKilos);


    //CAT
    @Query(value = "SELECT * "
            + "FROM pf_sku_items sku "
            + "INNER JOIN pf_pet_age_group gr ON gr.sku_id = sku.id " +
            "WHERE sku.animal_category_id = ?1 " +
            "AND sku.brand = ?2 " +
            "AND gr.group_name =  ?3 " +
            "AND sku.food_type_id = ?4 " +
            "AND sku.bestseller = ?5 " +
            "AND sku.package_weight_kilos = ?6 LIMIT 1", nativeQuery = true)
    SKUItem getRecommendedSKUItemForCat(Long animalCategoryId, String brand, String petGroup, Long foodTypeId,
            boolean bestseller, double packageWeightKilos);

    //OTHERS
    @Query(value = "SELECT * "
            + "FROM pf_sku_items " +
            "WHERE animal_category_id = ?1 " +
            "AND brand = ?2 " +
            "AND package_weight_kilos = ?3 " +
            "AND bestseller = ?4 LIMIT 1", nativeQuery = true)
    SKUItem getRecommendedSKUItemForOthers(Long animalCategoryId, String brand, double skuWeightKilos,
            boolean bestseller);


    //DOG Find the closest and LARGEST value similar to SKU weight in kilos in either direction:
    @Query(value = "SELECT sku.package_weight_kilos "
            + "FROM pf_pet_age_group gr "
            + "INNER JOIN pf_sku_items sku ON sku.id = gr.sku_id " +
            "WHERE sku.brand = ?1 " +
            "AND sku.package_weight_kilos >= ?2 " +
            "AND gr.group_name = ?3 " +
            "AND sku.food_type_id = ?4 " +
            "AND sku.animal_size = ?5 " +
            "AND sku.animal_category_id = ?6 " +
            "ORDER BY sku.package_weight_kilos LIMIT 1", nativeQuery = true)
    Double findClosestAndLargestSkuWeight(String brand, double weight, String pet_group, Long food_type_id,
            String animal_size, Long animalCategoryId);


    //CAT Find the closest and LARGEST value similar to SKU weight in kilos in either direction:
    @Query(value = "SELECT sku.package_weight_kilos "
            + "FROM pf_sku_items sku "
            + "INNER JOIN pf_pet_age_group gr ON sku.id = gr.sku_id " +
            "WHERE sku.brand = ?1 " +
            "AND sku.package_weight_kilos >= ?2 " +
            "AND gr.group_name = ?3 " +
            "AND sku.food_type_id = ?4 " +
            "AND sku.animal_category_id = ?5 " +
            "ORDER BY sku.package_weight_kilos LIMIT 1", nativeQuery = true)
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
    @Query(value = "SELECT sku.package_weight_kilos "
            + "FROM pf_pet_age_group gr "
            + "INNER JOIN pf_sku_items sku ON sku.id = gr.sku_id " +
            "WHERE sku.brand = ?1 " +
            "AND sku.package_weight_kilos <= ?2 " +
            "AND gr.group_name = ?3 " +
            "AND sku.food_type_id = ?4 " +
            "AND sku.animal_size = ?5 " +
            "AND sku.animal_category_id = ?6 " +
            "ORDER BY sku.package_weight_kilos DESC LIMIT 1", nativeQuery = true)
    Double findClosestAndLesserSkuWeightForDog(String brand, double weight, String pet_group, Long food_type_id,
            String animal_size, Long animalCategoryId);

    //CAT Find the closest and LESSER value similar to SKU weight in kilos in either direction:
    @Query(value = "SELECT sku.package_weight_kilos "
            + "FROM pf_sku_items sku "
            + "INNER JOIN pf_pet_age_group gr ON sku.id = gr.sku_id " +
            "WHERE sku.brand = ?1 " +
            "AND sku.package_weight_kilos <= ?2 " +
            "AND gr.group_name = ?3 " +
            "AND sku.food_type_id = ?4 " +
            "AND sku.animal_category_id = ?5 " +
            "ORDER BY sku.package_weight_kilos DESC LIMIT 1", nativeQuery = true)
    Double findClosestAndLesserSkuWeightForCat(String brand, double weight, String pet_group, Long food_type_id,
            Long animalCategoryId);


    @Query(value = "SELECT DISTINCT(brand) FROM pf_sku_items WHERE animal_category_id = ?", nativeQuery = true)
    List<String> findSKUBrandsByPetCategory(Long petCategoryId);

    List<SKUItem> findSKULineItemsByAnimalCategoryId(Long animalCategoryId);
}
