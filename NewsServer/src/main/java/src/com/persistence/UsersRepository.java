package src.com.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import src.com.domain.User;

public interface UsersRepository extends JpaRepository<User, Integer> {

    public User findTop1ByName(String name);

    public User findTop1ByEmail(String email);

}
