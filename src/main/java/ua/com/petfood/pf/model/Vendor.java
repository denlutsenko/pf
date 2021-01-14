package ua.com.petfood.pf.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pf_vendors")
public class Vendor extends PersistentEntity<Long> {
    private String title;

    public Vendor() {
        //Hibernate needs a default constructor
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
