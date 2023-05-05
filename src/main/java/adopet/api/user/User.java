package adopet.api.user;

import java.util.ArrayList;
import java.util.List;

import adopet.api.address.Address;
import adopet.api.pet.Pet;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String about;
    @Enumerated(EnumType.STRING)
    private UserType role;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "shelter",
               orphanRemoval = true,
               cascade = CascadeType.ALL)
    private List<Pet> pets;

    public User(UserRegistrationData userData, UserType role) {
        this.name = userData.name();
        this.email = userData.email();
        this.password = userData.password();
        this.phone = null;
        this.role = role;
        this.address = new Address();
    }

    public User(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.about = user.getAbout();
        this.role = user.getRole();
        if (user.getAddress() == null){
            this.address = new Address();
        } else {
            this.address = user.getAddress();
        }
        if (user.getPets() == null) {
            this.pets = new ArrayList<Pet>();
        } else {
            this.pets = user.getPets();
        }
    }

    public void updateData(@Valid UserUpdateData userData) {
        if (userData.name() != null) { this.name = userData.name(); }
        if (userData.email() != null) { this.email = userData.email(); }
        if (userData.phone() != null) { this.phone = userData.phone(); }
        if (userData.about() != null) { this.about = userData.about(); }
        if (userData.role() != null) { this.role = userData.role(); }
        if (userData.address() != null) { this.address = new Address(userData.address()); }
    }

}
