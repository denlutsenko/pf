package ua.com.petfood.pf.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pf_orders")
public class Order extends PersistentEntity<Long>{

    @ManyToOne
    private User user;

    private Long amount;

    @OneToMany
    private List<SKUItem> orderedItems;

    private LocalDateTime orderTime;

    public Order() {
        //Hibernate needs a default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public List<SKUItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<SKUItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
}
