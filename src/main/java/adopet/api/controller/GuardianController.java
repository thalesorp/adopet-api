package adopet.api.controller;

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

import adopet.api.user.User;
import adopet.api.user.UserData;
import adopet.api.user.UserListData;
import adopet.api.user.UserRegistrationData;
import adopet.api.user.UserRepository;
import adopet.api.user.UserType;
import adopet.api.user.UserUpdateData;
import jakarta.validation.Valid;

@RestController
@RequestMapping("guardians")
public class GuardianController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid UserRegistrationData userData, UriComponentsBuilder uriBuilder) {
        if (!userData.password().equals(userData.passwordConfirmation())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The password and password confirmation fields do not match");
        }
        if (userRepository.findByEmail(userData.email()).orElse(null) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This email is already registered");
        }
        var user = new User(userData, UserType.GUARDIAN);
        userRepository.save(user);
        var uri = uriBuilder.path("/guardians/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(uri).body(new UserData(new User(user)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        var user = userRepository.findByIdAndRole(id, UserType.GUARDIAN).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Guardian not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(new UserData(new User(user)));
    }

    @GetMapping
    public ResponseEntity<?> list(Pageable pages) {
        if (userRepository.count() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAllByRole(UserType.GUARDIAN, pages).map(UserListData::new));
    }

    @PutMapping @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid UserUpdateData userData) {
        var user = userRepository.findByIdAndRole(userData.id(), UserType.GUARDIAN).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Guardian not found");
        }
        if (!user.getEmail().equals(userData.email())) {
            if (userRepository.findByEmail(userData.email()).orElse(null) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("This email is already registered");
            }
        }
        user.updateData(userData);
        return ResponseEntity.status(HttpStatus.OK).body(new UserData(new User(user)));
    }

    @DeleteMapping("/{id}") @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        var user = userRepository.findByIdAndRole(id, UserType.GUARDIAN).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Guardian not found");
        }
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
