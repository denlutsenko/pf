package ua.com.petfood.pf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.com.petfood.pf.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM pf_users u " +
            "INNER JOIN pf_roles r ON u.role_id = r.id "
            + "WHERE r.name = ?1 AND u.status = ?2", nativeQuery = true)
    List<User> findAllUsersByRoleAndStatus(String role, String status);

    User findByEmail(String email);
}
