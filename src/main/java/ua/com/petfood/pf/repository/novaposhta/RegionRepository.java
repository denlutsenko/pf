package ua.com.petfood.pf.repository.novaposhta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.petfood.pf.model.novaposhta.RegionData;

@Repository
public interface RegionRepository extends JpaRepository<RegionData, String> {

}
