package es.netmind.banana_invoices.controller;

import es.netmind.banana_invoices.models.User;
import es.netmind.banana_invoices.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserServiceController {
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> products = userRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody @Valid User newUser) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String enc_password = newUser.getPassword(); //passwordEncoder.encode(newUser.getPassword());

        newUser.setId(null);
        newUser.setPassword(enc_password);
        userRepository.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

}
