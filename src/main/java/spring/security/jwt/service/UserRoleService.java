package spring.security.jwt.service;

import org.springframework.stereotype.Service;
import spring.security.jwt.bean.UserRole;
import spring.security.jwt.bean.dto.Role;

@Service
public interface UserRoleService {
    UserRole findByName(Role name);
}
