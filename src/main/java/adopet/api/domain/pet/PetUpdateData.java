package adopet.api.domain.pet;

import jakarta.validation.constraints.NotNull;

public record PetUpdateData(
    @NotNull Long id,
    String name,
    int age,
    String size,
    String temperament,
    boolean adopted,
    String imageURL,
    Long shelterId) {

}
