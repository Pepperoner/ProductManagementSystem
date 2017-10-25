package com.management.product.service;

import com.management.product.entity.User;
import com.management.product.enums.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public interface UserService extends DataService<User>, UserDetailsService {

    User getByUserName(String username);

    void removeByUsername(String username);

    Collection<User> getByRole(UserRole userRole);

    Collection<User> getAdmins();

    Collection<User> getUsers();

    User getAuthenticatedUser();

    boolean isAuthenticatedAdmin();
}