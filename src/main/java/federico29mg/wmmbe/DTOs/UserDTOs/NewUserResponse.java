package federico29mg.wmmbe.DTOs.UserDTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class NewUserResponse {
    private UUID id;
    private String username;
    private String email;
}
