package adopet.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import adopet.api.pet.Pet;
import adopet.api.pet.PetCreationData;
import adopet.api.pet.PetData;
import adopet.api.pet.PetRepository;
import adopet.api.pet.PetUpdateData;
import adopet.api.user.User;
import adopet.api.user.UserRepository;
import adopet.api.user.UserType;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pets")
public class PetController {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private UserRepository shelterRepository;

    @PostMapping @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid PetCreationData petData, UriComponentsBuilder uriBuilder) {
        var shelter = shelterRepository.findByIdAndRole(petData.shelterId(), UserType.SHELTER).orElse(null);
        if (shelter == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Shelter doesn't exist");
        }
        var pet = new Pet(petData, shelter);
        petRepository.save(pet);
        var uri = uriBuilder.path("/pets/{id}").buildAndExpand(pet.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(uri).body(new PetData(pet));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        var pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(new PetData(pet));
    }

    @GetMapping
    public ResponseEntity<?> list(Pageable pages){
        List<User> shelters = shelterRepository.findAllByRole(UserType.SHELTER);
        if (shelters.size() == 0) {
            return ResponseEntity.noContent().build();
        }
        if (petRepository.count() == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(petRepository.findAll(pages).map(PetData::new));
    }

    @PutMapping @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid PetUpdateData petData) {
        var pet = petRepository.findById(petData.id()).orElse(null);
        if (pet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found");
        }

        if (pet.getShelter().getId() != petData.shelterId()) {
            var shelter = shelterRepository.findByIdAndRole(petData.shelterId(), UserType.SHELTER).orElse(null);
            if (shelter == null) {
                return ResponseEntity.badRequest().body("New shelter doesn't exist");
            }
            pet.updateShelter(shelter);
        }

        pet.updateData(petData);
        return ResponseEntity.status(HttpStatus.OK).body(new PetData(pet));
    }

    @DeleteMapping("/{id}") @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        var pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found");
        }
        petRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
