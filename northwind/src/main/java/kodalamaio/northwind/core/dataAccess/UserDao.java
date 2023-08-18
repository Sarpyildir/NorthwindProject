package kodalamaio.northwind.core.dataAccess;

import kodalamaio.northwind.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmail(String email);

}
