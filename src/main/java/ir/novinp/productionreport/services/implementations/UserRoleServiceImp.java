package ir.novinp.productionreport.services.implementations;

import ir.novinp.productionreport.model.UserRole;
import ir.novinp.productionreport.model.repositories.UserRoleRepository;
import ir.novinp.productionreport.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImp implements UserRoleService {

    @Autowired
    private UserRoleRepository repository;

    @Override
    public UserRole save(UserRole userRole) {
        return repository.save(userRole);
    }
}
