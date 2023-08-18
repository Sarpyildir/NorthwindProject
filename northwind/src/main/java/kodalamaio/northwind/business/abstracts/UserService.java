package kodalamaio.northwind.business.abstracts;

import kodalamaio.northwind.core.entities.User;
import kodalamaio.northwind.core.utilities.results.DataResult;
import kodalamaio.northwind.core.utilities.results.Result;

public interface UserService {
    Result add(User user);
    DataResult<User> findByEmail(String email);
}
