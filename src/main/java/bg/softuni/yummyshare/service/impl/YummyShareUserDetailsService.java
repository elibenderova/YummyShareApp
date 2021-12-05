package bg.softuni.yummyshare.service.impl;

import bg.softuni.yummyshare.model.entity.UserEntity;
import bg.softuni.yummyshare.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.stream.Collectors;

@Service
public class YummyShareUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public YummyShareUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.
                findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " is not found!"));

        return mapToUserDetails(user);
    }

    private static UserDetails mapToUserDetails(UserEntity user) {
        Set<GrantedAuthority> authorities =
                user.
                        getRoles().
                        stream().
                        map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).
                        collect(Collectors.toUnmodifiableSet());

        return new User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
