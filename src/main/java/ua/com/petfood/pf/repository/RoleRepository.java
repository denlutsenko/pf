package ua.com.petfood.pf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.petfood.pf.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findOneByName(Enum roleName);
}
