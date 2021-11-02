package ua.com.petfood.pf.model.dto.novaposhta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BranchDataLoad {
    @JsonProperty("Ref")
    private String ref;
    @JsonProperty("SiteKey")
    private String siteKey;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("DescriptionRu")
    private String descriptionRu;
    @JsonProperty("ShortAddress")
    private String shortAddress;
    @JsonProperty("ShortAddressRu")
    private String shortAddressRu;
    @JsonProperty("Phone")
    private String phone;
    @JsonProperty("TypeOfWarehouse")
    private String typeOfWarehouse;
    @JsonProperty("Number")
    private String number;
    @JsonProperty("CityRef")
    private String cityRef;
    @JsonProperty("CityDescription")
    private String cityDescription;
    @JsonProperty("CityDescriptionRu")
    private String cityDescriptionRu;
    @JsonProperty("SettlementRef")
    private String settlementRef;
    @JsonProperty("SettlementDescription")
    private String settlementDescription;
    @JsonProperty("SettlementAreaDescription")
    private String settlementAreaDescription;
    @JsonProperty("SettlementRegionsDescription")
    private String settlementRegionsDescription;
    @JsonProperty("SettlementTypeDescription")
    private String settlementTypeDescription;
    @JsonProperty("SettlementTypeDescriptionRu")
    private String settlementTypeDescriptionRu;
    @JsonProperty("Longitude")
    private String longitude;
    @JsonProperty("Latitude")
    private String latitude;
    @JsonProperty("PostFinance")
    private int postFinance;
    @JsonProperty("BicycleParking")
    private int bicycleParking;
    @JsonProperty("PaymentAccess")
    private int paymentAccess;
    @JsonProperty("POSTerminal")
    private int POSTerminal;
    @JsonProperty("InternationalShipping")
    private int internationalShipping;
    @JsonProperty("SelfServiceWorkplacesCount")
    private int selfServiceWorkplacesCount;
    @JsonProperty("TotalMaxWeightAllowed")
    private int totalMaxWeightAllowed;
    @JsonProperty("PlaceMaxWeightAllowed")
    private int placeMaxWeightAllowed;
    @JsonIgnore
    private String sendingLimitationsOnDimensions;
    @JsonIgnore
    private String receivingLimitationsOnDimensions;
    @JsonIgnore
    private String reception;
    @JsonIgnore
    private String delivery;
    @JsonIgnore
    private String schedule;
    @JsonProperty("DistrictCode")
    private String districtCode;
    @JsonProperty("WarehouseStatus")
    private String warehouseStatus;
    @JsonProperty("WarehouseStatusDate")
    private String warehouseStatusDate;
    @JsonProperty("CategoryOfWarehouse")
    private String categoryOfWarehouse;
    @JsonProperty("Direct")
    private String direct;
    @JsonProperty("RegionCity")
    private String regionCity;
    @JsonProperty("WarehouseForAgent")
    private int warehouseForAgent;
    @JsonProperty("MaxDeclaredCost")
    private int maxDeclaredCost;
    @JsonProperty("WorkInMobileAwis")
    private int workInMobileAwis;

}
