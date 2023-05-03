package adopet.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import adopet.api.pet.Pet;
import adopet.api.pet.PetCreationData;
import adopet.api.pet.PetData;
import adopet.api.pet.PetRepository;
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
    public ResponseEntity<Object> create(@RequestBody @Valid PetCreationData petData, UriComponentsBuilder uriBuilder) {
        Long shelterId = petData.shelterId();
        var shelter = shelterRepository.findById(shelterId).orElse(null);
        if ((shelter == null) || (shelter.getRole() != UserType.SHELTER)) {
            return ResponseEntity.badRequest().body("Shelter doesn't exist");
        }

        var pet = new Pet(petData, shelter);
        petRepository.save(pet);
        var uri = uriBuilder.path("/pets/{id}").buildAndExpand(pet.getId()).toUri();
        return ResponseEntity.created(uri).body(new PetData(pet));
    }

}
