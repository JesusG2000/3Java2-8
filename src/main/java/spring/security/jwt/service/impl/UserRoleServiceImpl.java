package spring.security.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.security.jwt.bean.UserRole;
import spring.security.jwt.bean.dto.Role;
import spring.security.jwt.repository.UserRoleRepository;
import spring.security.jwt.service.UserRoleService;
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Override
    public UserRole findByName(Role name) {
       return userRoleRepository.findByName(name);
    }
}
