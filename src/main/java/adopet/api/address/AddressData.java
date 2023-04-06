package adopet.api.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressData(
    @NotBlank
    String street,
    String complement,
    @NotBlank
    String city,
    @NotBlank
    String state,
    @NotBlank
    @Pattern(regexp = "\\d{8}")
    String postalCode,
    @NotBlank
    String country) {

}
