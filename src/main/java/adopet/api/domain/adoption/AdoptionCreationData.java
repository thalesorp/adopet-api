package adopet.api.domain.adoption;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;

public record AdoptionCreationData(
    @NotNull Long petId,
    @NotNull Long guardianId,
    @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate date) {

}
