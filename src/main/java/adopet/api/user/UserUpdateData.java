package adopet.api.user;

import adopet.api.address.AddressData;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Digits;

public record UserUpdateData(
    @NotNull
    Long id,
    String name,
    String email,
    @Digits(integer = 11, fraction = 0)
    String phone,
    String about,
    UserType role,
    AddressData address
    ) {

}
