package bg.softuni.yummyshare.service.impl;

import bg.softuni.yummyshare.model.entity.UserEntity;
import bg.softuni.yummyshare.model.entity.UserRoleEntity;
import bg.softuni.yummyshare.model.entity.enums.UserRoleEnum;
import bg.softuni.yummyshare.model.service.UserLoginServiceModel;
import bg.softuni.yummyshare.model.service.UserRegistrationServiceModel;
import bg.softuni.yummyshare.model.view.UserViewModel;
import bg.softuni.yummyshare.repository.UserRepository;
import bg.softuni.yummyshare.repository.UserRoleRepository;
import bg.softuni.yummyshare.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final YummyShareUserDetailsService yummyShareUserService;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, YummyShareUserDetailsService yummyShareUserService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.yummyShareUserService = yummyShareUserService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initializeUsers() {
        if (userRepository.count() == 0) {
            UserRoleEntity adminRole = userRoleRepository.
                    findByRole(UserRoleEnum.ADMIN).
                    orElseThrow(() -> new IllegalStateException("Admin role could not be found"));

            UserRoleEntity userRole = userRoleRepository.
                    findByRole(UserRoleEnum.USER).
                    orElseThrow(() -> new IllegalStateException("User role could not be found"));

            UserEntity admin = new UserEntity();
            admin.
                    setFirstName("Admin").
                    setLastName("Adminov").
                    setUsername("admin").
                    setEmail("admin@gmail.com").
                    setPassword(passwordEncoder.encode("123456")).
                    setRoles(Set.of(adminRole, userRole));

            userRepository.save(admin);

            UserEntity user = new UserEntity();
            user.
                    setFirstName("User").
                    setLastName("Userov").
                    setUsername("user").
                    setEmail("user@gmail.com").
                    setPassword(passwordEncoder.encode("123456")).
                    setRoles(Set.of(userRole));

            userRepository.save(user);
        }
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {
        UserEntity user = modelMapper.map(userRegistrationServiceModel, UserEntity.class);
        user.setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()));

        UserRoleEntity userRole = userRoleRepository.
                findByRole(UserRoleEnum.USER).
                orElseThrow(() -> new IllegalStateException("Sorry, but this user role does not exist!"));

        user.setRoles(Set.of(userRole));
        user = userRepository.save(user);

        UserDetails principal = yummyShareUserService.loadUserByUsername(user.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                user.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.
                getContext().
                setAuthentication(authentication);
    }

    @Override
    public Set<UserViewModel> getAllUsers() {
        return userRepository.
                findAll().
                stream().
                map(userEntity -> modelMapper.map(userEntity, UserViewModel.class)).
                collect(Collectors.toSet());
    }

    @Override
    public UserViewModel findUserViewModelByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).orElse(null);

        UserViewModel profile = modelMapper.map(user, UserViewModel.class);

        profile.
                setUsername(user.getUsername()).
                setEmail(user.getEmail()).
                setFirstName(user.getFirstName()).
                setLastName(user.getLastName()).
                setRoles(user.getRoles());

        return profile;
    }

    @Override
    public UserLoginServiceModel findByUsername(String name) {
        return null;
    }
}
