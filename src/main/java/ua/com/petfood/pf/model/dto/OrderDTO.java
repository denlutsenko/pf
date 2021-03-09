package ua.com.petfood.pf.model.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {
    private Long animalId;
    private List<OrderSKUItems> orderSkuItems;

    public OrderDTO(){}

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(final Long animalId) {
        this.animalId = animalId;
    }

    public List<OrderSKUItems> getOrderSkuItems() {
        return orderSkuItems;
    }

    public void setOrderSkuItems(final List<OrderSKUItems> orderSkuItems) {
        this.orderSkuItems = orderSkuItems;
    }
}
