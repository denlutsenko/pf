package ua.com.petfood.pf.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeliveryAddressDTO {
    @JsonProperty("regionRef")
    private String regionRef;
    @JsonProperty("region")
    private String region;
    @JsonProperty("cityRef")
    private String cityRef;
    @JsonProperty("city")
    private String city;
    @JsonProperty("branchRef")
    private String branchRef;
    @JsonProperty("branch")
    private String branch;
    @JsonProperty("description")
    private String description;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("email")
    private String email;
    @JsonProperty("orderId")
    private String orderId;
}
