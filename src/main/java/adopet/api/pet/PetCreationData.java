package adopet.api.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetCreationData(
    @NotBlank String name,
    @NotNull int age,
    @NotBlank String size,
    @NotBlank String temperament,
    @NotNull boolean adopted,
    String imageURL,
    @NotNull Long shelterId) {

}
