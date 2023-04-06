package adopet.api.address;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private String complement;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public Address(AddressData addressData) {
        this.street = addressData.street();
        this.complement = addressData.complement();
        this.city = addressData.city();
        this.state = addressData.state();
        this.postalCode = addressData.postalCode();
        this.country = addressData.country();
    }

}
