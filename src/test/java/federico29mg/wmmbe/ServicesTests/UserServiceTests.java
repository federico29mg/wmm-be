package federico29mg.wmmbe.ServicesTests;

import federico29mg.wmmbe.DTOs.UserDTOs.NewUserRequest;
import federico29mg.wmmbe.DTOs.UserDTOs.UserResponse;
import federico29mg.wmmbe.Entities.Receipt;
import federico29mg.wmmbe.Entities.User;
import federico29mg.wmmbe.Exceptions.UserExceptions.EmailAlreadyExistsException;
import federico29mg.wmmbe.Exceptions.UserExceptions.UserNotFoundException;
import federico29mg.wmmbe.Exceptions.UserExceptions.UsernameAlreadyExistsException;
import federico29mg.wmmbe.Mappers.UserMapper;
import federico29mg.wmmbe.Repositories.UserRepository;
import federico29mg.wmmbe.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    private NewUserRequest newUserRequest;
    private User newUser;
    private User user;
    private UserResponse userResponse;

    @BeforeEach
    public void setUp() {
        newUserRequest = NewUserRequest.builder()
                .username("federico")
                .email("federico@wmm.com")
                .password("123")
                .build();

        newUser = User.builder()
                .username(newUserRequest.getUsername())
                .email(newUserRequest.getEmail())
                .password(newUserRequest.getPassword())
                .build();

        Set<Receipt> receipts = new HashSet<>();

        user = User.builder()
                .id(UUID.randomUUID())
                .username(newUser.getUsername())
                .email(newUser.getEmail())
                .password(newUser.getPassword())
                .receipts(receipts)
                .build();

        userResponse = UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    @Test
    public void givenValidNewUserRequest_whenSavingNewUser_thenReturnNewUserResponse() {
        when(userRepository.findByUsernameIs(newUserRequest.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByEmailIs(newUserRequest.getEmail())).thenReturn(Optional.empty());
        when(userMapper.newUserRequestToUser(newUserRequest)).thenReturn(newUser);
        when(userRepository.save(newUser)).thenReturn(user);
        when(userMapper.userToUserResponse(user)).thenReturn(userResponse);

        UserResponse testUserResponse = userService.postUser(newUserRequest);

        verify(userRepository, times(1)).findByUsernameIs(newUserRequest.getUsername());
        verify(userRepository, times(1)).findByEmailIs(newUserRequest.getEmail());
        verify(userMapper, times(1)).newUserRequestToUser(newUserRequest);
        verify(userRepository, times(1)).save(newUser);
        verify(userMapper, times(1)).userToUserResponse(user);

        assertThat(testUserResponse).isNotNull();
    }

    @Test
    public void givenNewUserRequestWithAlreadyTakenUsername_whenSavingNewUser_theThrowEmailAlreadyExistsException() {
        when(userRepository.findByUsernameIs(newUserRequest.getUsername())).thenReturn(Optional.of(user));

        assertThrows(UsernameAlreadyExistsException.class, () -> {
            userService.postUser(newUserRequest);
        });
    }

    @Test
    public void givenNewUserRequestWithAlreadyTakenEmail_whenSavingNewUser_theThrowEmailAlreadyExistsException() {
        when(userRepository.findByUsernameIs(newUserRequest.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByEmailIs(newUserRequest.getEmail())).thenReturn(Optional.of(user));

        assertThrows(EmailAlreadyExistsException.class, () -> {
            userService.postUser(newUserRequest);
        });
    }

    @Test
    public void givenUserUUID_whenDeletingUser_thenDeleteUser() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        doNothing().when(userRepository).delete(user);

        userService.deleteUser(user.getId());

        verify(userRepository, times(1)).findById(user.getId());
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void givenNonExistentUserUUID_whenDeletingUser_theThrowUserNotFoundException() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.deleteUser(user.getId());
        });
    }
}
