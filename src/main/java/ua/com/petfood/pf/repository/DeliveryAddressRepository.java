package ua.com.petfood.pf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.petfood.pf.model.DeliveryAddress;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

}
