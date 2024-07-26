package pl.aibron.quests.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User postUser(@RequestBody User user) {
        return userService.saveDetails(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllDetails();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getOneDetail(id);
    }

    @PutMapping("/{id}")
    public User updateUserById(@PathVariable int id, @RequestBody User quest) {
        return userService.updateDetail(id, quest);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable int id) {
        userService.deleteOneDetail(id);
    }
}

