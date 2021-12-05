package bg.softuni.yummyshare.web;

import bg.softuni.yummyshare.model.entity.UserEntity;
import bg.softuni.yummyshare.model.view.ArticleViewModel;
import bg.softuni.yummyshare.model.view.UserViewModel;
import bg.softuni.yummyshare.repository.ArticleRepository;
import bg.softuni.yummyshare.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserRestController {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserRestController(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserViewModel>> findAll() {
        return ResponseEntity.
                ok().
                body(userRepository.
                        findAll().
                        stream().
                        map(u -> modelMapper.map(u, UserViewModel.class)).
                        collect(Collectors.toList()));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<UserEntity> delete(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
