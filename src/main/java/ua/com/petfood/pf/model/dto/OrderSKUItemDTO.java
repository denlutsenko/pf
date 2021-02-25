package ua.com.petfood.pf.model.dto;

public class OrderSKUItemDTO {

    private Long skuItemId;
    private int quantity;

    public OrderSKUItemDTO(){}

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
