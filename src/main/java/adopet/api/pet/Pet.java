package adopet.api.pet;


import adopet.api.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
@EqualsAndHashCode(of = "id")
public class Pet {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String size;
    private String temperament;
    private boolean adopted;
    private String imageURL;
    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private User shelter;

    public Pet(PetCreationData petData, User shelter) {
        this.name = petData.name();
        this.age = petData.age();
        this.size = petData.size();
        this.temperament = petData.temperament();
        this.adopted = petData.adopted();
        this.imageURL = petData.imageURL();
        this.shelter = shelter;
    }
}
