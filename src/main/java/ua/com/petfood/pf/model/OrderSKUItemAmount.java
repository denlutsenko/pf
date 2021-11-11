package ua.com.petfood.pf.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sku_item_amount")
public class OrderSKUItemAmount extends PersistentEntity<Long> {

    @ManyToOne
    @JsonBackReference
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skuItem_id", nullable = false)
    private SKUItem skuItem;

    @Column(name = "quantity")
    private Integer quantity;

    public OrderSKUItemAmount() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public SKUItem getSkuItem() {
        return skuItem;
    }

    public void setSkuItem(SKUItem skuItem) {
        this.skuItem = skuItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
