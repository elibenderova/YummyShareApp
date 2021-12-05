package bg.softuni.yummyshare.service.impl;

import bg.softuni.yummyshare.model.entity.UserRoleEntity;
import bg.softuni.yummyshare.model.entity.enums.UserRoleEnum;
import bg.softuni.yummyshare.repository.UserRoleRepository;
import bg.softuni.yummyshare.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void initializeRoles() {
        if(userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);
            userRoleRepository.save(adminRole);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRoleEnum.USER);
            userRoleRepository.save(userRole);
        }
    }
}
