package ua.com.petfood.pf.model.dto.novaposhta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
public class RegionDataLoad {
    @JsonProperty("Ref")
    private String ref;
    @JsonProperty("AreasCenter")
    private String areasCenter;
    @JsonProperty("DescriptionRu")
    private String descriptionRu;
    @JsonProperty("Description")
    private String description;
}




