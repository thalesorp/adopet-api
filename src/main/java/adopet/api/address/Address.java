package adopet.api.address;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    private String street;
    private String complement;
    private String city;
    private String state;
    @Column(name = "postal_code")
    private String postalCode;
    private String country;

    public Address(AddressData addressData) {
        if (addressData.street() != null) { this.street = addressData.street(); }
        if (addressData.complement() != null) { this.complement = addressData.complement(); }
        if (addressData.city() != null) { this.city = addressData.city(); }
        if (addressData.state() != null) { this.state = addressData.state(); }
        if (addressData.postalCode() != null) { this.postalCode = addressData.postalCode(); }
        if (addressData.country() != null) { this.country = addressData.country(); }
    }

}
