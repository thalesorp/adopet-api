package adopet.api.adoption;


import java.time.LocalDate;

import adopet.api.pet.Pet;
import adopet.api.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "adoptions")
public class Adoption {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "pet_id")
    private Pet pet;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "guardian_id")
    private User guardian;
    @Column(name = "adoption_date")
    private LocalDate date;

    public Adoption(@Valid Pet pet, User guardian, LocalDate date) {
        this.pet = pet;
        this.guardian = guardian;
        this.date = date;
    }

}
