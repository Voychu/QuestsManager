package pl.aibron.quests.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.aibron.quests.config.JWTGenerator;


import java.util.List;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    private AuthenticationManager authenticationManager;

    private JWTGenerator jwtGenerator;

    @Autowired
    public UserController(UserService userService, JWTGenerator jwtGenerator, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody User user){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public User postUser(@RequestBody User user) {
        return userService.saveDetails(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllDetails();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getOneDetail(id);
    }

    @PutMapping("/users/{id}")
    public User updateUserById(@PathVariable int id, @RequestBody User quest) {
        return userService.updateDetail(id, quest);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable int id) {
        userService.deleteOneDetail(id);
    }
}

