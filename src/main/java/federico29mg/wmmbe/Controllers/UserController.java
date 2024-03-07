package federico29mg.wmmbe.Controllers;

import federico29mg.wmmbe.DTOs.UserDTOs.NewUserRequest;
import federico29mg.wmmbe.DTOs.UserDTOs.NewUserResponse;
import federico29mg.wmmbe.Services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public NewUserResponse postUser(@Valid @RequestBody NewUserRequest newUserRequest) {
        return userService.postUser(newUserRequest);
    }
    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@NotNull @PathVariable UUID uuid) {
        userService.deleteAccount(uuid);
    }
}
