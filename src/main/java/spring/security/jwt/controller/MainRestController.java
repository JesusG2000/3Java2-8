package spring.security.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.security.jwt.config.jwt.JwtProvider;
import spring.security.jwt.bean.User;
import spring.security.jwt.controller.dto.AuthRequest;
import spring.security.jwt.controller.dto.AuthResponse;
import spring.security.jwt.controller.dto.RegistrationRequest;
import spring.security.jwt.controller.dto.UserResponse;
import spring.security.jwt.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.List;


@RestController
public class MainRestController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/users")
    public List<User> getUsers() {
        return userServiceImpl.findAll();
    }

    @GetMapping("/patient/1")
    public List<User> getPat() {
        return userServiceImpl.findAll();
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        if(!userServiceImpl.existsUserByLogin(registrationRequest.getLogin())) {
            User u = new User();
            u.setPassword(registrationRequest.getPassword());
            u.setLogin(registrationRequest.getLogin());
            u.setEmail(registrationRequest.getEmail());
            userServiceImpl.saveUser(u);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody AuthRequest request) throws IOException {
        User user = userServiceImpl.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if(user!=null && user.isActive()) {
            String token = jwtProvider.generateToken(user.getLogin());
            AuthResponse response = new AuthResponse(token, user.getUserRole().getName());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping ("/authorized")
    public ResponseEntity<?> isAuthorized() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/getUser/{jwt}")
    public UserResponse getUser(@PathVariable(name="jwt") String jwt){
        String userName =jwtProvider.getLoginFromToken(jwt);
        User user = userServiceImpl.findByLogin(userName);
        return new UserResponse(user.getId() , user.getLogin(),user.getUserRole().getName());
    }



}
