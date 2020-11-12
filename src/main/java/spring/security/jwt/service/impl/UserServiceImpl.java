package spring.security.jwt.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.security.jwt.bean.User;
import spring.security.jwt.bean.UserRole;
import spring.security.jwt.bean.dto.Role;
import spring.security.jwt.repository.UserRepository;
import spring.security.jwt.repository.UserRoleRepository;
import spring.security.jwt.service.UserService;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailSenderImpl mailSender;

    public User saveUser(User user) {
        UserRole userRole = userRoleRepository.findByName(Role.ROLE_PATIENT);
        user.setUserRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivationCode(UUID.randomUUID().toString());

        user.setActive(false);
        String message = String.format(
                "Hello %s!\n" +
                        "activate your code , need to visit http://localhost:8080/activate/%s",
                user.getLogin(),
                user.getActivationCode()
        );
        mailSender.send(user.getEmail(),"Activation code" , message);
        return userRepository.save(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public boolean existsUserByLogin(String login) {
        return userRepository.existsUserByLogin(login);
    }

    public boolean existsUserByLoginAndPassword(String login , String password){
        return findByLoginAndPassword(login,password)!=null;
    }

    @Override
    public User findById(long id) {
        return userRepository.getById(id);
    }


    public boolean activateUser(String code) {
      User user = userRepository.findByActivationCode(code);

      if(user ==null){
          return false;
      }
      user.setActivationCode(null);
      user.setActive(true);
      userRepository.save(user);

      return true;
    }
}
