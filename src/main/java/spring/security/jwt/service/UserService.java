package spring.security.jwt.service;

import org.springframework.stereotype.Service;
import spring.security.jwt.bean.User;

import java.util.List;

@Service
public interface UserService {
    User saveUser(User user);

    User findByLogin(String login);

    List<User> findAll();

    User findByLoginAndPassword(String login, String password);

    boolean existsUserByLogin(String login);

    boolean existsUserByLoginAndPassword(String login, String password);

    User findById(long id);
}
