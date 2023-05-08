package adopet.api.domain.pet;

import adopet.api.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "pets")
public class Pet {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String size;
    private String temperament;
    private boolean adopted;
    private String imageURL;
    @ManyToOne @JoinColumn(name = "shelter_id")
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

    public void updateData(@Valid PetUpdateData petData) {
        if (petData.name() != null) { this.name = petData.name(); }
        this.age = petData.age();
        if (petData.size() != null) { this.size = petData.size(); }
        if (petData.temperament() != null) { this.temperament = petData.temperament(); }
        this.adopted = petData.adopted();
        if (petData.imageURL() != null) { this.imageURL = petData.imageURL(); }
    }

    public void updateShelter(User shelter) {
        this.shelter = shelter;
    }

    public void updateAdoption(Boolean adopted) {
        this.adopted = adopted;
    }

}
