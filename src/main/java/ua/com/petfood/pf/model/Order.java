package ua.com.petfood.pf.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pf_orders")
public class Order extends PersistentEntity<Long> {

    @ManyToOne
    private User user;

    @ManyToOne
    private Animal animal;

    private BigDecimal orderAmount;

    private Date orderTime;

    private String order_id;

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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(final Date orderTime) {
        this.orderTime = orderTime;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(final Animal animal) {
        this.animal = animal;
    }
}
