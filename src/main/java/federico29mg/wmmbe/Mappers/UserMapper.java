package federico29mg.wmmbe.Mappers;

import federico29mg.wmmbe.DTOs.UserDTOs.UserResponse;
import federico29mg.wmmbe.DTOs.UserDTOs.NewUserRequest;
import federico29mg.wmmbe.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface UserMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "receipts", ignore = true)
    })
    User newUserRequestToUser(NewUserRequest newUserRequest);
    UserResponse userToUserResponse(User user);
    List<UserResponse> userListToUserResponseList(List<User> users);
}
