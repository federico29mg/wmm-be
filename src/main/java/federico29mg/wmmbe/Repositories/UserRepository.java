package federico29mg.wmmbe.Repositories;

import federico29mg.wmmbe.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameIs(String username);
    Optional<User> findByEmailIs(String email);
}
