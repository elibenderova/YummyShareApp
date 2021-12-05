package bg.softuni.yummyshare.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegistrationBindingModel {
    @Size(min = 1, message = "First name must be more that 1 character. Please try again!")
    private String firstName;
    @Size(min = 1, message = "Last name must be more that 1 character. Please try again!")
    private String lastName;
    @NotEmpty(message = "Email is required. Please try again!")
    @Email(message = "Email is not valid. Please try again!")
    private String email;
    @Size(min = 5, max = 30, message = "Username must be between 5 and 20 characters. Please try again!")
    private String username;
    @Size(min = 6, message = "Password must be more than 6 characters. Please try again!")
    private String password;
    @Size(min = 6, message = "Password must be more than 6 characters. Please try again!")
    private String confirmPassword;

    public UserRegistrationBindingModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
