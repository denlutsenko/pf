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
@Table(name = "pf_np_city")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CityData {
    @Id
    @Column(name = "ref")
    private String ref;
    @Column(name = "description")
    private String description;
    @Column(name = "description_ru")
    private String descriptionRu;
    @Column(name = "delivery1")
    private String delivery1;
    @Column(name = "delivery2")
    private String delivery2;
    @Column(name = "delivery3")
    private String delivery3;
    @Column(name = "delivery4")
    private String delivery4;
    @Column(name = "delivery5")
    private String delivery5;
    @Column(name = "delivery6")
    private String delivery6;
    @Column(name = "delivery7")
    private String delivery7;
    @Column(name = "area")
    private String area;
    @Column(name = "settlement_type")
    private String settlementType;
    @Column(name = "is_branch")
    private boolean isBranch;
    @Column(name = "prevent_entry_new_streets_user")
    private String preventEntryNewStreetsUser;
    @Column(name = "city_id")
    private String cityID;
    @Column(name = "settlement_type_description")
    private String settlementTypeDescription;
    @Column(name = "settlement_type_description_ru")
    private String settlementTypeDescriptionRu;
    @Column(name = "special_cash_check")
    private int specialCashCheck;
    @Column(name = "area_description")
    private String areaDescription;
    @Column(name = "area_description_ru")
    private String areaDescriptionRu;
}
