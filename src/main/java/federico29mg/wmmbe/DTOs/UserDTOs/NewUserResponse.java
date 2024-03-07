package federico29mg.wmmbe.DTOs.UserDTOs;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewUserResponse {
    private UUID id;
    private String username;
    private String email;
}
