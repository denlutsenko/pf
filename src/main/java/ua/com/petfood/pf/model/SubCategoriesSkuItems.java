package ua.com.petfood.pf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pf_animal_sub_categories_sku_items")
@Getter
@Setter
public class SubCategoriesSkuItems{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    protected Long id;

    @ManyToOne
    @JsonManagedReference
    public AnimalSubCategory animalSubCategory;

    @ManyToOne
    @JsonBackReference
    public SKUItem skuItem;

    public SubCategoriesSkuItems(){}

}
