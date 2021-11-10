package ua.com.petfood.pf.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pf_animal_sub_category")
@Getter
@Setter
public class AnimalSubCategory extends PersistentEntity<Long> {

    private String subCategoryName;

    @OneToMany(mappedBy = "animalSubCategory")
    @JsonBackReference
    private List<SubCategoriesSkuItems> subCategoriesSkuItems;

    public AnimalSubCategory() {
    }
}
