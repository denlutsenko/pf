package ua.com.petfood.pf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.petfood.pf.model.SKUPrice;

import java.util.List;
import java.util.Optional;

@Repository
public interface SKUPriceRepository extends JpaRepository<SKUPrice, Long> {
    Optional<SKUPrice> findById(Long id);

    List<SKUPrice> findAllById(List<Long> ids);
}
