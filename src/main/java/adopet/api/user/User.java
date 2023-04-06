package adopet.api.user;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    public User(UserRegistrationData userData) {
        this.name = userData.name();
        this.email = userData.email();
        this.password = userData.password();
    }

    public void updateData(@Valid UserUpdateData userData) {
        if (userData.name() != null) {
            this.name = userData.name();
        }
        if (userData.email() != null) {
        this.email = userData.email();
        }
    }

}
