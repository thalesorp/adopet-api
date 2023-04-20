package adopet.api.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegistratedData(
    @NotBlank String name,
    @NotBlank @Email String email) {

        public UserRegistratedData(User user) {
            this(
                user.getName(),
                user.getEmail()
            );
        }

}
