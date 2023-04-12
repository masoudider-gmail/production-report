package ir.novinp.productionreport.services;

import ir.novinp.productionreport.model.AppUser;

public interface AppUserService {

    AppUser save(AppUser appUser);

    AppUser findByUserName(String userName);
}
