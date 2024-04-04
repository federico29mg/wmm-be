package federico29mg.wmmbe.Controllers;

import federico29mg.wmmbe.DTOs.UserDTOs.UserResponse;
import federico29mg.wmmbe.DTOs.UserDTOs.NewUserRequest;
import federico29mg.wmmbe.Services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse postUser(@Valid @RequestBody NewUserRequest newUserRequest) {
        return userService.postUser(newUserRequest);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PatchMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse patchUsername(@PathVariable UUID uuid, @RequestParam String username) {
        return userService.patchUsername(uuid, username);
    }

    @GetMapping("/{uuid}")
    public UserResponse getUser(@PathVariable UUID uuid) {
        return userService.getUser(uuid);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@NotNull @PathVariable UUID uuid) {
        userService.deleteUser(uuid);
    }
}
