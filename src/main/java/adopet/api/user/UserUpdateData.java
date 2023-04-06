package adopet.api.user;

import jakarta.validation.constraints.NotNull;

public record UserUpdateData(
    @NotNull Long id,
    String name,
    String email) {

}
