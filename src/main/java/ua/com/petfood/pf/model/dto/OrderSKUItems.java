package ua.com.petfood.pf.model.dto;

public class OrderSKUItems {

    private Long skuItemId;
    private int quantity;

    // TODO добавить id животного

    public OrderSKUItems() {
    }

    public Long getSkuItemId() {
        return skuItemId;
    }

    public void setSkuItemId(final Long skuItemId) {
        this.skuItemId = skuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
}
