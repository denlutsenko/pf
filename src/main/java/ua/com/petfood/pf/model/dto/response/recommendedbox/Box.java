package ua.com.petfood.pf.model.dto.response.recommendedbox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Box {
    private String brand;
    private BigDecimal totalPrice;
    private List<Map> lineItems = new ArrayList<>();

    public Box() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(final BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Map> getLineItems() {
        return lineItems;
    }

    public void setLineItems(final List<Map> lineItems) {
        this.lineItems = lineItems;
    }
}
