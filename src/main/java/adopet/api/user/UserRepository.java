package adopet.api.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByRole(UserType role);

    Page<User> findAllByRole(UserType role, Pageable pageable);

    Optional<User> findByIdAndRole(Long id, UserType guardian);

    long countByRole(UserType role);

    Optional<User> findByEmail(String email);

}
