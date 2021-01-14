package ua.com.petfood.pf.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pf_sku_prices")
public class SKUPrice extends PersistentEntity<Long> {
    @OneToOne
    private SKUItem skuItem;
    @ManyToOne
    private Vendor vendor;
    private Long priceInKop;

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

    public Long getPriceInKop() {
        return priceInKop;
    }

    public void setPriceInKop(Long priceInKop) {
        this.priceInKop = priceInKop;
    }
}
