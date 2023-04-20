package adopet.api.address;

import jakarta.validation.constraints.Pattern;

public record AddressData(
    String street,
    String complement,
    String city,
    String state,
    @Pattern(regexp = "\\d{8}")
    String postalCode,
    String country) {

    public AddressData(Address address) {
        this(
            address.getStreet(),
            address.getComplement(),
            address.getCity(),
            address.getState(),
            address.getPostalCode(),
            address.getCountry()
        );
    }

}
