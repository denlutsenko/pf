package ua.com.petfood.pf.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "pf_jwt_black_list")
public class JwtBlacklist extends PersistentEntity<Long> {

    private String token;
    @Column(name = "expired")
    private Date expired;

}