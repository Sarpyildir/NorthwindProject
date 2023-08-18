package kodalamaio.northwind.business.concretes;

import kodalamaio.northwind.business.abstracts.UserService;
import kodalamaio.northwind.core.dataAccess.UserDao;
import kodalamaio.northwind.core.entities.User;
import kodalamaio.northwind.core.utilities.results.DataResult;
import kodalamaio.northwind.core.utilities.results.Result;
import kodalamaio.northwind.core.utilities.results.SuccessDataResult;
import kodalamaio.northwind.core.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {
    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        super();
        this.userDao = userDao;
    }
    @Override
    public Result add(User user) {
        this.userDao.save(user);
        return new SuccessResult("User is added.");
    }

    @Override
    public DataResult<User> findByEmail(String email) {
        return new SuccessDataResult<User>(this.userDao.findByEmail(email), "User is fetched.");
    }
}
