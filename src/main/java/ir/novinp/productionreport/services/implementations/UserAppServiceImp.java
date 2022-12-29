package ir.novinp.productionreport.services.implementations;

import ir.novinp.productionreport.model.AppUser;
import ir.novinp.productionreport.model.UserRole;
import ir.novinp.productionreport.model.repositories.AppUserRepository;
import ir.novinp.productionreport.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author M.Ider
 */
@Service
public class UserAppServiceImp implements AppUserService, UserDetailsService {

    @Autowired
    private AppUserRepository repository;

    @Override
    public AppUser save(AppUser appUser) {
        return repository.save(appUser);
    }

    @Override
    public AppUser findByUserName(String userName) {
        return repository.findAppUserByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User Name Not Found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser = findByUserName(username);

        List<SimpleGrantedAuthority> collect = appUser
                .getRoles()
                .stream()
                .map(UserRole::getRoleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User(appUser.getUserName(), appUser.getPassword(), collect);
    }
}
