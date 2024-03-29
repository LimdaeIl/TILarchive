package security.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.example.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // email 로 사용자 정보를 가져옵니다.
}
