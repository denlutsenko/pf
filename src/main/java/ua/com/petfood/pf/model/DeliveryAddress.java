package ua.com.petfood.pf.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "pf_delivery_address")
public class DeliveryAddress extends PersistentEntity<Long> {

    private String regionRef;

    private String cityRef;

    private String branchRef;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String orderId;

    private Long userId;
}
