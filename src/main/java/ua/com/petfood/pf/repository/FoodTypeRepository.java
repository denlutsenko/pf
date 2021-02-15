package ua.com.petfood.pf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.petfood.pf.model.FoodType;

@Repository
public interface FoodTypeRepository extends JpaRepository<FoodType, Long> {

    @Query(value = "SELECT type FROM pf_food_types where code=?", nativeQuery = true)
    String findFoodTypeByCode(int code);

}
