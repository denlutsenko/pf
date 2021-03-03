package ua.com.petfood.pf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.com.petfood.pf.model.SKUPrice;

@Repository
public interface SKUPriceRepository extends JpaRepository<SKUPrice, Long> {

    @Query(value = "SELECT * FROM pf_sku_prices "
            + "INNER JOIN pf_sku_items ON pf_sku_prices.sku_item_id = pf_sku_items.id "
            + "WHERE pf_sku_items.animal_category_id = ?", nativeQuery = true)
    List<SKUPrice> findSkuPricesByPetCategory(Long id);

}
