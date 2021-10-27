package ua.com.petfood.pf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.petfood.pf.model.DailyFoodAmount;
import ua.com.petfood.pf.model.DeliveryAddress;
import ua.com.petfood.pf.model.User;

import java.util.List;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

    List<DeliveryAddress> findAllByUserId(Long id);
}
