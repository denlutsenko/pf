package ua.com.petfood.pf.model.dto;

public class QuestionnaireDto {
    private Long petCategoryId;
    private String name;
    private int age;
    private Long adultWeightId;
    private Long preferableFoodId;
    private Long purchaseFrequencyId;
    private int recommendedMealAmount;

    public QuestionnaireDto() {
    }

    public Long getPetCategoryId() {
        return petCategoryId;
    }

    public void setPetCategoryId(Long petCategoryId) {
        this.petCategoryId = petCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getAdultWeightId() {
        return adultWeightId;
    }

    public void setAdultWeightId(Long adultWeightId) {
        this.adultWeightId = adultWeightId;
    }

    public Long getPreferableFoodId() {
        return preferableFoodId;
    }

    public void setPreferableFoodId(Long preferableFoodId) {
        this.preferableFoodId = preferableFoodId;
    }

    public Long getPurchaseFrequencyId() {
        return purchaseFrequencyId;
    }

    public void setPurchaseFrequencyId(Long purchaseFrequencyId) {
        this.purchaseFrequencyId = purchaseFrequencyId;
    }

    public int getRecommendedMealAmount() {
        return recommendedMealAmount;
    }

    public void setRecommendedMealAmount(int recommendedMealAmount) {
        this.recommendedMealAmount = recommendedMealAmount;
    }
}
