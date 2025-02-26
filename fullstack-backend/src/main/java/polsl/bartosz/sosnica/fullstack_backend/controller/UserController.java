package polsl.bartosz.sosnica.fullstack_backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import polsl.bartosz.sosnica.fullstack_backend.interfaces.UserInterfaces.IUserRepository;
import polsl.bartosz.sosnica.fullstack_backend.model.UserModel;

@RestController
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/user")
    UserModel newUser(@RequestBody UserModel newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/getnull")
    public ResponseEntity<Map<String, String>> getString() {
        return ResponseEntity.ok(Map.of("message", "nullowy"));
    }

}
