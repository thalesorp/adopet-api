package adopet.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import adopet.api.user.User;
import adopet.api.user.UserData;
import adopet.api.user.UserListData;
import adopet.api.user.UserRegistratedData;
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
    public ResponseEntity<Object> create(@RequestBody @Valid UserRegistrationData userData) {
        if (!userData.password().equals(userData.passwordConfirmation())) {
            return ResponseEntity.badRequest().body("The password and password confirmation fields do not match");
        }
        var user = new User(userData, UserType.GUARDIAN);
        userRepository.save(user);
        return ResponseEntity.ok(new UserRegistratedData(user));
    }

    @DeleteMapping("/{id}") @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<Object>> list(Pageable pages) {
        if (userRepository.count() == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userRepository.findAll(pages).map(UserListData::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        /* Erro na linha abaixo!
         * Se utilizar a classe "UserData", que possui um atributo "AddressData", recebo o erro:
         * "Cannot invoke \"adopet.api.address.Address.getStreet()\" because \"address\" is null"
        */
        var userData = new UserListData(user);
        //var userData = new UserData(user);
        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    @PutMapping @Transactional
    public ResponseEntity<Object> update(@RequestBody @Valid UserUpdateData userData) {
        var user = userRepository.findById(userData.id()).orElse(null);
        if (user == null){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        user = userRepository.getReferenceById(userData.id());
        user.updateData(userData);
        return ResponseEntity.ok(new UserData(userData));
    }

}
