package ua.com.petfood.pf.repository.novaposhta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.petfood.pf.model.novaposhta.CityData;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityData, String> {
    List<CityData> findByAreaOrderByDescription(String areaCode);
}
