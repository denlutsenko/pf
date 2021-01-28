package ua.com.petfood.pf.model;

import javax.persistence.*;

@Entity
@Table(name = "pf_sku_items")
public class SKUItem extends PersistentEntity<Long> {
    private String packageCode;
    private String eanCode;
    private Integer amountInPackage;
    private String originCountry;
    private String manufacturer;
    private String brand;
    private String title;

    @OneToOne
    private AnimalCategory animalCategory;

    @Enumerated(EnumType.STRING)
    private Group petGroup;

    @OneToOne
    private FoodType foodType;

    private Integer packageWeightKilos;
    /*
    Will be used only for dos SKU:
        DEFAULT_VALUE ("0"), kg
        SMALL ("0 - 2.00"), kg
        MIDDLE ("2.01 - 10.00"), kg
        BIG ("10.01 - 32.00"), kg
        HUGE ("32.01 - 99.99"); kg

    This field for 'Cat', 'Fish' etc. will be empty or some default value
     */
    private String animalSize;

    private Boolean grainLess;
    private Boolean forPregnant;
    private Boolean forCastrated;
    private Boolean forGreatFurAndSkin;
    private Boolean forGreatBonesAndJoints;
    private Boolean forSensitiveDigestion;
    private Integer bestBeforeMonths;
    private String image;

    public SKUItem() {
        //Hibernate needs a default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AnimalCategory getAnimalCategory() {
        return animalCategory;
    }

    public void setAnimalCategory(AnimalCategory animalCategory) {
        this.animalCategory = animalCategory;
    }

    public Group getPetGroup() {
        return petGroup;
    }

    public void setPetGroup(Group petGroup) {
        this.petGroup = petGroup;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public Integer getPackageWeightKilos() {
        return packageWeightKilos;
    }

    public void setPackageWeightKilos(Integer packageWeightKilos) {
        this.packageWeightKilos = packageWeightKilos;
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

    public String getAnimalSize() {
        return animalSize;
    }

    public void setAnimalSize(String animalSize) {
        this.animalSize = animalSize;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
