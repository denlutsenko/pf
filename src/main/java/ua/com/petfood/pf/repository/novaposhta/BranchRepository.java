package ua.com.petfood.pf.repository.novaposhta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.petfood.pf.model.novaposhta.BranchData;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<BranchData, String> {
    List<BranchData> findByCityRefOrderByDescription(String cityCode);
}
