package federico29mg.wmmbe.DTOs.UserDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewUserRequest {
    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email has to have a valid form")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
