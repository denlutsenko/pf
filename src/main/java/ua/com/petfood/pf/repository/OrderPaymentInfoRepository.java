package ua.com.petfood.pf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.com.petfood.pf.model.OrderPaymentInfo;

@Repository
public interface OrderPaymentInfoRepository extends JpaRepository<OrderPaymentInfo, Long> {

    @Query(value = "SELECT * FROM pf_order_payment_info WHERE order_id=?1", nativeQuery = true)
    List<OrderPaymentInfo> findAllSubscriptionPaymentsForOrder(String orderId);

    @Query(value = "SELECT * FROM pf_order_payment_info WHERE order_id=?1", nativeQuery = true)
    OrderPaymentInfo findPaymentDetailsForOrder(String orderId);
}
