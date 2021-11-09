package ua.com.petfood.pf.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "pf_sku_items")
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

    @OneToOne
    private AnimalSubCategory animalSubCategory;

    @OneToMany (mappedBy = "sku")
    @JsonManagedReference
    private List<PetAgeGroup> petAgeGroups;

    @OneToOne
    private FoodType foodType;

    @OneToOne
    private FoodSubType foodSubType;

    private double packageWeightKilos;

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

    public List<OrderSKUItemAmount> getOrderSKUItemAmount() {
        return orderSKUItemAmount;
    }

    public void setOrderSKUItemAmount(List<OrderSKUItemAmount> orderSKUItemAmount) {
        this.orderSKUItemAmount = orderSKUItemAmount;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getEanCode() {
        return eanCode;
    }

    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    public Integer getAmountInPackage() {
        return amountInPackage;
    }

    public void setAmountInPackage(Integer amountInPackage) {
        this.amountInPackage = amountInPackage;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public AnimalCategory getAnimalCategory() {
        return animalCategory;
    }

    public void setAnimalCategory(AnimalCategory animalCategory) {
        this.animalCategory = animalCategory;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public double getPackageWeightKilos() {
        return packageWeightKilos;
    }

    public void setPackageWeightKilos(double packageWeightKilos) {
        this.packageWeightKilos = packageWeightKilos;
    }

    public String getAnimalSize() {
        return animalSize;
    }

    public void setAnimalSize(String animalSize) {
        this.animalSize = animalSize;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public Boolean getGrainLess() {
        return grainLess;
    }

    public void setGrainLess(Boolean grainLess) {
        this.grainLess = grainLess;
    }

    public Boolean getForPregnant() {
        return forPregnant;
    }

    public void setForPregnant(Boolean forPregnant) {
        this.forPregnant = forPregnant;
    }

    public Boolean getForCastrated() {
        return forCastrated;
    }

    public void setForCastrated(Boolean forCastrated) {
        this.forCastrated = forCastrated;
    }

    public Boolean getForGreatFurAndSkin() {
        return forGreatFurAndSkin;
    }

    public void setForGreatFurAndSkin(Boolean forGreatFurAndSkin) {
        this.forGreatFurAndSkin = forGreatFurAndSkin;
    }

    public Boolean getForGreatBonesAndJoints() {
        return forGreatBonesAndJoints;
    }

    public void setForGreatBonesAndJoints(Boolean forGreatBonesAndJoints) {
        this.forGreatBonesAndJoints = forGreatBonesAndJoints;
    }

    public Boolean getForSensitiveDigestion() {
        return forSensitiveDigestion;
    }

    public void setForSensitiveDigestion(Boolean forSensitiveDigestion) {
        this.forSensitiveDigestion = forSensitiveDigestion;
    }

    public Integer getBestBeforeMonths() {
        return bestBeforeMonths;
    }

    public void setBestBeforeMonths(Integer bestBeforeMonths) {
        this.bestBeforeMonths = bestBeforeMonths;
    }

    public Boolean getBestseller() {
        return bestseller;
    }

    public void setBestseller(Boolean bestseller) {
        this.bestseller = bestseller;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSmallImgPath() {
        return smallImgPath;
    }

    public void setSmallImgPath(final String smallImgPath) {
        this.smallImgPath = smallImgPath;
    }

    public String getLargeImgPath() {
        return largeImgPath;
    }

    public void setLargeImgPath(final String largeImgPath) {
        this.largeImgPath = largeImgPath;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(final String imgName) {
        this.imgName = imgName;
    }

    public FoodSubType getFoodSubType() {
        return foodSubType;
    }

    public void setFoodSubType(final FoodSubType foodSubType) {
        this.foodSubType = foodSubType;
    }

    public AnimalSubCategory getAnimalSubCategory() {
        return animalSubCategory;
    }

    public void setAnimalSubCategory(final AnimalSubCategory animalSubCategory) {
        this.animalSubCategory = animalSubCategory;
    }

    public List<PetAgeGroup> getPetAgeGroups() {
        return petAgeGroups;
    }

    public void setPetAgeGroups(final List<PetAgeGroup> petAgeGroups) {
        this.petAgeGroups = petAgeGroups;
    }

    public List<AnimalSizeType> getAnimalSizes() {
        return animalSizes;
    }

    public void setAnimalSizes(final List<AnimalSizeType> animalSizes) {
        this.animalSizes = animalSizes;
    }
}
