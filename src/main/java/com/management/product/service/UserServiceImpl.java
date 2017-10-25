package com.management.product.service;

import com.management.product.entity.User;
import com.management.product.enums.UserRole;
import com.management.product.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
public class UserServiceImpl extends DataServiceImpl<User> implements UserService {

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUserName(String username) throws IllegalArgumentException, NullPointerException {
        if (isBlank(username)) {
            throw new IllegalArgumentException("Incorrect name of user");
        }
        final User user = repository.findByUsername(username);
        if (user == null) {
            throw new NullPointerException("Haven`t user with such name" + username + " in database");
        }

        return user;
    }

    @Override
    @Transactional
    public void removeByUsername(String username) {
        if (isNotBlank(username)) {
            repository.deleteByUsername(username);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<User> getByRole(UserRole userRole) throws IllegalArgumentException {
        if (userRole == null) {
            throw new IllegalArgumentException("Entered user`s role is null");
        }
        return repository.findAllByRole(userRole);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<User> getAdmins() {
        return repository.findAllByRole(UserRole.ADMIN);
    }

    @Override
    public Collection<User> getUsers() {
        return repository.findAllByRole(UserRole.USER);
    }

    @Override
    public User getAuthenticatedUser() {
        User user;
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException e) {
            user = new User("anonymousUser");
        }
        return user;
    }

    @Override
    public boolean isAuthenticatedAdmin() {
        return getAuthenticatedUser().getRole().equals(UserRole.ADMIN);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws IllegalArgumentException, UsernameNotFoundException {
        User user;
        try {
            user = getByUserName(username);
        } catch (NullPointerException e){
            throw  new UsernameNotFoundException(e.getMessage());
        }
        return user;
    }
}