package ua.com.petfood.pf.model.novaposhta;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "pf_np_region")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class RegionData  {
    @Id
    @Column(name = "ref")
    private String ref;
    @Column(name = "areas_center")
    private String areasCenter;
    @Column(name = "description_ru")
    private String descriptionRu;
    @Column(name = "description")
    private String description;


}
