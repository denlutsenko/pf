package ua.com.petfood.pf.model;

import static ua.com.petfood.pf.helper.constants.Constants.WAITING_PAYMENT;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pf_orders")
public class Order extends PersistentEntity<Long> {

    @ManyToOne
    private User user;

    @ManyToOne
    private Animal animal;

    @OneToOne
    private DeliveryAddress deliveryAddress;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(final String order_id) {
        this.order_id = order_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getOrderCreationDate() {
        return orderCreationDate;
    }

    public void setOrderCreationDate(final Date orderCreationDate) {
        this.orderCreationDate = orderCreationDate;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(final Animal animal) {
        this.animal = animal;
    }

    public boolean isSubscription() {
        return subscription;
    }

    public void setSubscription(final boolean subscription) {
        this.subscription = subscription;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(final String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(final String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getOrderPaymentDate() {
        return orderPaymentDate;
    }

    public void setOrderPaymentDate(final Date orderPaymentDate) {
        this.orderPaymentDate = orderPaymentDate;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
