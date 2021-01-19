package ua.com.petfood.pf.model;

import javax.persistence.*;

@Entity
@Table(name = "pf_roles")
public class Role extends PersistentEntity<Long>{

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleName name;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private UserStatus status = UserStatus.ACTIVE;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
