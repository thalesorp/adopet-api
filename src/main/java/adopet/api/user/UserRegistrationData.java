package adopet.api.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegistrationData(
    @NotBlank String name,
    @NotBlank @Email String email,
    @NotBlank String password,
    @NotBlank String passwordConfirmation) {

}
