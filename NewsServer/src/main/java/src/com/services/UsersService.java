package src.com.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.com.domain.User;
import src.com.persistence.UsersRepository;

@Service
public class UsersService {
    private UsersRepository repository;

    @Autowired
    public UsersService(UsersRepository repository) {
        this.repository = repository;
    }

    public User doLogin(String email, String password) {
        User usr = repository.findTop1ByEmail(email);
        if (null == usr) {
            throw new RuntimeException("User does not exist");
        }
        if (usr.getPassword().equals(password)) {
            return usr;
        }
        throw new RuntimeException("Access denied");
    }
}