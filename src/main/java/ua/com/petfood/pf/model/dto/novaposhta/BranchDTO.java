package ua.com.petfood.pf.model.dto.novaposhta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BranchDTO {
    @JsonIgnore
    private boolean success;
    @JsonProperty("data")
    private List<BranchDataLoad> branchDataLoad;
    @JsonIgnore
    private String errors;
    @JsonIgnore
    private String warnings;
    @JsonIgnore
    private String info;
    @JsonIgnore
    private String messageCodes;
    @JsonIgnore
    private String errorCodes;
    @JsonIgnore
    private String warningCodes;
    @JsonIgnore
    private String infoCodes;
}
