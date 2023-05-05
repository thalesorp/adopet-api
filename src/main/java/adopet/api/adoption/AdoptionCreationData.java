package adopet.api.adoption;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;

public record AdoptionCreationData(
    @NotNull Long petId,
    @NotNull Long guardianId,
    @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate date) {
    //@DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date) {

}
