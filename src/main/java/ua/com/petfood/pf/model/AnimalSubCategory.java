package ua.com.petfood.pf.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "pf_animal_sub_categories")
public class AnimalSubCategory extends PersistentEntity<Long> {

    private String subCategoryName;

    @ManyToOne
    @JsonBackReference
    private SKUItem skuItem;

    public AnimalSubCategory() {
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(final String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public SKUItem getSkuItem() {
        return skuItem;
    }

    public void setSkuItem(final SKUItem skuItem) {
        this.skuItem = skuItem;
    }
}
