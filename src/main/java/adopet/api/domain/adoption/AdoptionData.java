package adopet.api.domain.adoption;

import java.time.LocalDate;

public record AdoptionData(
    Long id,
    Long petId,
    Long guardianId,
    LocalDate date) {

    public AdoptionData(Adoption adoption) {
        this(
            adoption.getId(),
            adoption.getPet().getId(),
            adoption.getGuardian().getId(),
            adoption.getDate()
        );
    }

}
