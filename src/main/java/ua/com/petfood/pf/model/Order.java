package ua.com.petfood.pf.model;

import static ua.com.petfood.pf.helper.constants.Constants.WAITING_PAYMENT;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pf_orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    protected Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Animal animal;

    @OneToOne
    private DeliveryAddress deliveryAddress;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<OrderSKUItemAmount> orderItems;

    private BigDecimal orderAmount;
    private Date orderCreationDate;
    private Date orderPaymentDate;
    private String order_id;
    private boolean subscription = false;
    private String subscriptionStatus;
    private String paymentStatus = WAITING_PAYMENT;

    public Order() {
        //Hibernate needs a default constructor
    }
}
