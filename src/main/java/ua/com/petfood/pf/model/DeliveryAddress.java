package ua.com.petfood.pf.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "pf_delivery_address")
public class DeliveryAddress extends PersistentEntity<Long> {

    private String regionRef;
    private String region;
    private String cityRef;
    private String city;
    private String branchRef;
    private String branch;
    private String description;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    @Column(name = "orderId", unique = true)
    private String orderId;

}
