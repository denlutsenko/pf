package ua.com.petfood.pf.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "pf_sku_prices")
public class SKUPrice extends PersistentEntity<Long> {
    @OneToOne
    private SKUItem skuItem;
    @ManyToOne
    private Vendor vendor;

    private BigDecimal basePrice;
    private BigDecimal sellingPrice;

    public SKUPrice() {
        //Hibernate needs a default constructor
    }

    public SKUItem getSkuItem() {
        return skuItem;
    }

    public void setSkuItem(SKUItem skuItem) {
        this.skuItem = skuItem;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
