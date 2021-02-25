package ua.com.petfood.pf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.com.petfood.pf.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
