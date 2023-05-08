package adopet.api.domain.user;

import java.util.List;
import java.util.stream.Collectors;

import adopet.api.domain.address.AddressData;
import adopet.api.domain.pet.PetData;
import jakarta.validation.Valid;

public record UserData(
    Long id,
    String name,
    String email,
    String phone,
    String about,
    UserType role,
    AddressData address,
    List<PetData> pets) {

    public UserData(User user) {
        this(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getPhone(),
            user.getAbout(),
            user.getRole(),
            new AddressData(user.getAddress()),
            user.getPets().stream().map(PetData::new).collect(Collectors.toList())
        );
    }

    public UserData(@Valid UserUpdateData user) {
        this(
            user.id(),
            user.name(),
            user.email(),
            user.phone(),
            user.about(),
            user.role(),
            user.address(),
            user.pets()
        );
    }

}
