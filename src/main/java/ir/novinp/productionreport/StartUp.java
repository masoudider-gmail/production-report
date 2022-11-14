package ir.novinp.productionreport;

import ir.novinp.productionreport.model.Order;
import ir.novinp.productionreport.model.AppUser;
import ir.novinp.productionreport.model.UserRole;
import ir.novinp.productionreport.services.OrderService;
import ir.novinp.productionreport.services.AppUserService;
import ir.novinp.productionreport.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StartUp implements CommandLineRunner {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        UserRole userRole = UserRole
                .builder()
                .roleName("ROLE_ADMIN")
                .build();

        AppUser appUser = AppUser
                .builder()
                .name("Masoud")
                .lastName("Ider")
                .nationalCode("4640096178")
                .address("Brn")
                .phoneNumber("09139887321")
                .userName("ider")
                .password(encoder.encode("123"))
                .role(userRole)
                .build();

        Order order = Order
                .builder()
                .name("MR_Ider")
                .windowCount(4)
                .windowMeter(19.5)
                .glassCount(8)
                .glassMeter(12.0)
                .picture("sth")
                .glassProductionStatus(1)
                .windowProductionStatus(1)
                .appUser(appUser)
                .build();

        userRoleService.save(userRole);
        appUserService.save(appUser);
        orderService.save(order);
    }
}
