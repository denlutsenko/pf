package ua.com.petfood.pf.model;

import javax.persistence.*;

@Entity
@Table(name = "pf_animals")
public class Animal extends PersistentEntity<Long> {

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private AnimalCategory category;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    public Animal() {
        //Hibernate needs a default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnimalCategory getCategory() {
        return category;
    }

    public void setCategory(AnimalCategory category) {
        this.category = category;
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
}
