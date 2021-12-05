package bg.softuni.yummyshare.init;

import bg.softuni.yummyshare.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRoleService userRoleService;
    private final UserService userService;

    public DataInitializer(UserRoleService userRoleService, UserService userService) {
        this.userRoleService = userRoleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userRoleService.initializeRoles();
        userService.initializeUsers();
    }
}
