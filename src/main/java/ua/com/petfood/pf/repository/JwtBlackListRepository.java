package ua.com.petfood.pf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.petfood.pf.model.JwtBlacklist;

@Repository
public interface JwtBlackListRepository extends JpaRepository<JwtBlacklist, Long> {

    boolean existsByToken(String token);
}
