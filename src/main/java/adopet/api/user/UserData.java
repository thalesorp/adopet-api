package adopet.api.user;

import adopet.api.address.AddressData;
import jakarta.validation.Valid;

public record UserData(
    String name,
    String email,
    String phone,
    String about,
    UserType role,
    AddressData address) {

    public UserData(User user) {
        this(
            user.getName(),
            user.getEmail(),
            user.getPhone(),
            user.getAbout(),
            user.getRole(),
            new AddressData(user.getAddress())
        );
    }

    public UserData(@Valid UserUpdateData user) {
        this(
            user.name(),
            user.email(),
            user.phone(),
            user.about(),
            user.role(),
            user.address()
        );
    }

}
