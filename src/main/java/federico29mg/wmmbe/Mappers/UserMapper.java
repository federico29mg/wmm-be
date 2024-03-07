package federico29mg.wmmbe.Mappers;

import federico29mg.wmmbe.DTOs.UserDTOs.NewUserRequest;
import federico29mg.wmmbe.DTOs.UserDTOs.NewUserResponse;
import federico29mg.wmmbe.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface UserMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "receipts", ignore = true)
    })
    User newUserRequestToUser(NewUserRequest newUserRequest);
    NewUserResponse userToNewUserResponse(User user);
}
