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

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        UserRole userRole = userRoleRepository.findByName(Role.ROLE_PATIENT);
        user.setUserRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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


}
