package federico29mg.wmmbe.Services;

import federico29mg.wmmbe.DTOs.UserDTOs.NewUserRequest;
import federico29mg.wmmbe.DTOs.UserDTOs.NewUserResponse;
import federico29mg.wmmbe.Entities.Receipt;
import federico29mg.wmmbe.Entities.User;
import federico29mg.wmmbe.Exceptions.UserExceptions.EmailAlreadyExistsException;
import federico29mg.wmmbe.Exceptions.UserExceptions.UserNotFoundException;
import federico29mg.wmmbe.Exceptions.UserExceptions.UsernameAlreadyExistsException;
import federico29mg.wmmbe.Mappers.UserMapper;
import federico29mg.wmmbe.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public NewUserResponse postUser(NewUserRequest newUserRequest) {
        if(userRepository.findByUsernameIs(newUserRequest.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username " + newUserRequest.getUsername() + " is already in use");
        }
        if(userRepository.findByEmailIs(newUserRequest.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email " + newUserRequest.getEmail() + " is already in use");
        }
        User user = userRepository.save(userMapper.newUserRequestToUser(newUserRequest));
        return userMapper.userToNewUserResponse(user);
    }
    public void deleteAccount(UUID uuid) {
        userRepository.delete(findUserById(uuid));
    }

    public Receipt saveUserReceipt(Receipt receipt) {
        User user = findUserById(receipt.getUser().getId());
        user.addReceipt(receipt);
        user = userRepository.save(user);
        return user.getReceipts().stream().findFirst().get();
    }

    public void deleteUserReceipt(Receipt receipt) {
        User user = findUserById(receipt.getUser().getId());
        user.removeReceipt(receipt);
        userRepository.save(user);
    }

    public Set<Receipt> getUserReceipts(UUID uuid) {
        User user = findUserById(uuid);
        return user.getReceipts();
    }

    private User findUserById(UUID user_id) {
        Optional<User> user = userRepository.findById(user_id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return user.get();
    }
}
