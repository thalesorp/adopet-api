package adopet.api.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import adopet.api.adoption.Adoption;
import adopet.api.adoption.AdoptionCreationData;
import adopet.api.adoption.AdoptionData;
import adopet.api.adoption.AdoptionRepository;
import adopet.api.pet.PetRepository;
import adopet.api.user.UserRepository;
import adopet.api.user.UserType;
import jakarta.validation.Valid;

@RestController
@RequestMapping("adoptions")
public class AdoptionController {

    @Autowired
    private AdoptionRepository adoptionRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private UserRepository guardianRepository;

    @PostMapping @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid AdoptionCreationData adoptionData, UriComponentsBuilder uriBuilder) {
        var pet = petRepository.findById(adoptionData.petId()).orElse(null);
        if (pet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found");
        }
        if (pet.isAdopted()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This pet is already adopted");
        }

        var guardian = guardianRepository.findByIdAndRole(adoptionData.guardianId(), UserType.GUARDIAN).orElse(null);
        if (guardian == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Guardian not found");
        }

        LocalDate adoptionDate = adoptionData.date();
        if (adoptionDate == null) {
            adoptionDate = LocalDate.now();
        }

        var adoption = new Adoption(pet, guardian, adoptionDate);
        adoptionRepository.save(adoption);
        pet.updateAdoption(true);

        var uri = uriBuilder.path("/adoptions/{id}").buildAndExpand(adoption.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(uri).body(new AdoptionData(adoption));
    }

    @DeleteMapping("/{id}") @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        var adoption = adoptionRepository.findById(id).orElse(null);
        if (adoption == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Adoption not found");
        }
        var pet = petRepository.findById(adoption.getPet().getId()).orElse(null);
        if (pet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found");
        }
        adoptionRepository.deleteById(id);
        pet.updateAdoption(false);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
