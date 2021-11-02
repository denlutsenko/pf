package ua.com.petfood.pf.model.novaposhta;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pf_np_branch")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BranchData {
    @Id
    @Column(name = "ref")
    private String ref;
    @Column(name = "site_key")
    private String siteKey;
    @Column(name = "description")
    private String description;
    @Column(name = "description_ru")
    private String descriptionRu;
    @Column(name = "short_address")
    private String shortAddress;
    @Column(name = "short_address_ru")
    private String shortAddressRu;
    @Column(name = "phone")
    private String Phone;
    @Column(name = "type_of_warehouse")
    private String typeOfWarehouse;
    @Column(name = "number")
    private String Number;
    @Column(name = "city_ref")
    private String cityRef;
    @Column(name = "city_description")
    private String cityDescription;
    @Column(name = "city_description_ru")
    private String cityDescriptionRu;
    @Column(name = "settlement_ref")
    private String settlementRef;
    @Column(name = "settlement_description")
    private String settlementDescription;
    @Column(name = "settlement_area_description")
    private String settlementAreaDescription;
    @Column(name = "settlement_regions_description")
    private String settlementRegionsDescription;
    @Column(name = "settlement_type_description")
    private String settlementTypeDescription;
    @Column(name = "settlement_type_description_ru")
    private String settlementTypeDescriptionRu;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "post_finance")
    private int postFinance;
    @Column(name = "bicycle_parking")
    private int bicycleParking;
    @Column(name = "payment_access")
    private int paymentAccess;
    @Column(name = "posterminal")
    private int POSTerminal;
    @Column(name = "international_shipping")
    private int internationalShipping;
    @Column(name = "self_service_workplaces_count")
    private int selfServiceWorkplacesCount;
    @Column(name = "total_max_weight_allowed")
    private int totalMaxWeightAllowed;
    @Column(name = "place_max_weight_allowed")
    private int placeMaxWeightAllowed;
    //    @OneToOne
//    private SendingLimitationsOnDimensions sendingLimitationsOnDimensions;
//    @OneToOne
//    private ReceivingLimitationsOnDimensions receivingLimitationsOnDimensions;
//    @OneToOne
//    private Reception reception;
//    @OneToOne
//    private Delivery delivery;
//    @OneToOne
//    private Schedule schedule;
    @Column(name = "district_code")
    private String districtCode;
    @Column(name = "warehouse_status")
    private String warehouseStatus;
    @Column(name = "warehouse_status_date")
    private String warehouseStatusDate;
    @Column(name = "category_of_warehouse")
    private String categoryOfWarehouse;
    @Column(name = "direct")
    private String direct;
    @Column(name = "region_city")
    private String regionCity;
    @Column(name = "warehouse_for_agent")
    private int warehouseForAgent;
    @Column(name = "max_declared_cost")
    private int maxDeclaredCost;
    @Column(name = "work_in_mobile_awis")
    private int WorkInMobileAwis;

}
