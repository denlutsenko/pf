package ua.com.petfood.pf.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pf_animal_sub_category")
public class AnimalSubCategory extends PersistentEntity<Long> {

    private String subCategoryName;

    public AnimalSubCategory() {
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(final String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }
}
