package ua.com.petfood.pf.model;

import javax.persistence.*;

@Entity
@Table(name = "pf_animals")
public class Animal extends PersistentEntity<Long> {

    @OneToOne
    private AnimalCategory animalCategory;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name = "name")
    private String name;

    private String subCategoryName;

    @Column(name = "age")
    private Integer age;

    private String size;

    public Animal() {
        //Hibernate needs a default constructor
    }

    public Animal(final AnimalCategory animalCategory, final User user, final String name, String subCategoryName,
            Integer age, String size) {
        this.animalCategory = animalCategory;
        this.user = user;
        this.name = name;
        this.subCategoryName = subCategoryName;
        this.age = age;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnimalCategory getAnimalCategory() {
        return animalCategory;
    }

    public void setAnimalCategory(AnimalCategory animalCategory) {
        this.animalCategory = animalCategory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    private String getSubCategoryName() {
        return subCategoryName;
    }

    private void setSubCategoryName(final String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }
}
