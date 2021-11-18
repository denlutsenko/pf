package ua.com.petfood.pf.model.dto;

import lombok.Data;

@Data
public class UserProfileDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String created;
}
