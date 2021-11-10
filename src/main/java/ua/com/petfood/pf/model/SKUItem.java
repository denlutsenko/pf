package ua.com.petfood.pf.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pf_sku_items")
@Getter
@Setter
public class SKUItem extends PersistentEntity<Long> {
    @JsonIgnore
    private String packageCode;
    @JsonIgnore
    private String eanCode;
    @JsonIgnore
    private Integer amountInPackage;
    private String originCountry;
    //производитель
    private String manufacturer;
    private String brand;
    private String skuName;
    private String taste;
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne
    private AnimalCategory animalCategory;

    @OneToMany (mappedBy = "skuItem")
    @JsonManagedReference
    private List<SubCategoriesSkuItems> animalSubCategories;

    @OneToMany (mappedBy = "sku")
    @JsonManagedReference
    private List<PetAgeGroup> petAgeGroups;

    @OneToOne
    private FoodType foodType;

    @OneToOne
    private FoodSubType foodSubType;

    @Column(columnDefinition = "double default 0")
    private double packageWeightKilos;
    @Column(columnDefinition = "double default 0")
    private double packageVolumeLiter;

    /*
    Will be used only for dog SKU:
        DEFAULT_VALUE ("0"), kg
        SMALL ("0 - 2.00"), kg
        MIDDLE ("2.01 - 10.00"), kg
        BIG ("10.01 - 32.00"), kg
        HUGE ("32.01 - 99.99"); kg

    This field for 'Cat', 'Fish' etc. will be empty or some default value
     */
    @JsonIgnore
    private String animalSize;

    @OneToMany (mappedBy = "skuItemForDog")
    @JsonManagedReference
    private List<AnimalSizeType> animalSizes;

    @JsonIgnore
    private Boolean grainLess;
    @JsonIgnore
    private Boolean forPregnant;
    @JsonIgnore
    private Boolean forCastrated;
    @JsonIgnore
    private Boolean forGreatFurAndSkin;
    @JsonIgnore
    private Boolean forGreatBonesAndJoints;
    @JsonIgnore
    private Boolean forSensitiveDigestion;
    @JsonIgnore
    private Boolean bestseller;
    @JsonIgnore
    private Integer bestBeforeMonths; // ???

    private String smallImgPath;
    private String largeImgPath;
    private String imgName;

    @OneToMany
    @JsonIgnore
    private List<OrderSKUItemAmount> orderSKUItemAmount;

    public SKUItem() {
        //Hibernate needs a default constructor
    }
}
