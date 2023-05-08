package adopet.api.domain.user;

import java.util.List;

import adopet.api.domain.address.AddressData;
import adopet.api.domain.pet.PetData;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public record UserUpdateData(
    @NotNull
    Long id,
    String name,
    String email,
    @Digits(integer = 11, fraction = 0)
    String phone,
    String about,
    UserType role,
    AddressData address,
    List<PetData> pets
    ) {

}
