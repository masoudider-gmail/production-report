package ir.novinp.productionreport.services;

import ir.novinp.productionreport.model.AppUser;

import java.util.Optional;

public interface AppUserService {

    AppUser save(AppUser appUser);

    AppUser findByUserName(String userName);
}
