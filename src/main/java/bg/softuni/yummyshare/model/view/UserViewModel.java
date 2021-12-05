package bg.softuni.yummyshare.model.view;

import bg.softuni.yummyshare.model.entity.UserRoleEntity;

import java.util.Set;

public class UserViewModel {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Set<UserRoleEntity> roles;

    public UserViewModel() {
    }

    public Long getId() {
        return id;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserViewModel setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
