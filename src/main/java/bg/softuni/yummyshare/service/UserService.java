package bg.softuni.yummyshare.service;

import bg.softuni.yummyshare.model.service.UserLoginServiceModel;
import bg.softuni.yummyshare.model.service.UserRegistrationServiceModel;
import bg.softuni.yummyshare.model.view.UserViewModel;

import java.util.Set;

public interface UserService {
    void initializeUsers();
    void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);
    Set<UserViewModel> getAllUsers();
    UserViewModel findUserViewModelByUsername(String username);
    UserLoginServiceModel findByUsername(String name);
}
