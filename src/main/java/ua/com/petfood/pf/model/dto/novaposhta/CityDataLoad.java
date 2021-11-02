package ua.com.petfood.pf.model.dto.novaposhta;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CityDataLoad {
    @JsonProperty("Description")
    private String description;
    @JsonProperty("DescriptionRu")
    private String descriptionRu;
    @JsonProperty("Ref")
    private String ref;
    @JsonProperty("Delivery1")
    private String delivery1;
    @JsonProperty("Delivery2")
    private String delivery2;
    @JsonProperty("Delivery3")
    private String delivery3;
    @JsonProperty("Delivery4")
    private String delivery4;
    @JsonProperty("Delivery5")
    private String delivery5;
    @JsonProperty("Delivery6")
    private String delivery6;
    @JsonProperty("Delivery7")
    private String delivery7;
    @JsonProperty("Area")
    private String area;
    @JsonProperty("SettlementType")
    private String settlementType;
    @JsonProperty("IsBranch")
    private String isBranch;
    @JsonProperty("PreventEntryNewStreetsUser")
    private String preventEntryNewStreetsUser;
    @JsonProperty("CityID")
    private String cityID;
    @JsonProperty("SettlementTypeDescription")
    private String settlementTypeDescription;
    @JsonProperty("SettlementTypeDescriptionRu")
    private String settlementTypeDescriptionRu;
    @JsonProperty("SpecialCashCheck")
    private int specialCashCheck;
    @JsonProperty("AreaDescription")
    private String areaDescription;
    @JsonProperty("AreaDescriptionRu")
    private String areaDescriptionRu;

}
