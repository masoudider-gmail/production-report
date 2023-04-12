package ir.novinp.productionreport.model.repositories;

import ir.novinp.productionreport.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
