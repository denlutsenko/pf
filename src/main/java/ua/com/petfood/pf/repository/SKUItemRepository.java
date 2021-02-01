package ua.com.petfood.pf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.petfood.pf.model.SKUItem;

@Repository
public interface SKUItemRepository extends JpaRepository<SKUItem, Long> {

    @Query(value = "", nativeQuery = true)
    SKUItem getRecommendedSKUItemForDog();
}
