package ua.com.petfood.pf.model.dto;

import lombok.Data;
import ua.com.petfood.pf.helper.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class UserDTO {
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Please provide a valid email address")
    private String username;
    @ValidPassword
    @NotBlank(message = "Password is mandatory")
    @Size(min = 3, max = 50)
    private String password;

}
