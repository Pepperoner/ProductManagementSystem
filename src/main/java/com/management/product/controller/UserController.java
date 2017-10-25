package com.management.product.controller;

import com.management.product.entity.User;
import com.management.product.enums.UserRole;
import com.management.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan(basePackages = "com.management.product.service")
public class UserController {

    private final UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            value = "/user/new",
            method = RequestMethod.GET
    )
    public ModelAndView getNewUserPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles", UserRole.values());
        modelAndView.addObject("is_admin", this.userService.isAuthenticatedAdmin());
        modelAndView.setViewName("add_user");
        return modelAndView;
    }

    @RequestMapping(
            value = "/user/add",
            method = RequestMethod.POST)
    public String addNewUser(
            @RequestParam(value = "username", defaultValue = "") String username,
            @RequestParam(value = "password", defaultValue = "") String password,
            @RequestParam(value = "role", defaultValue = "USER") UserRole role,
            @RequestParam(value = "locked", defaultValue = "false") boolean isLocked
    ) {
        User userToAdd = new User(username, passwordEncoder().encode(password), role);
        userToAdd.setLocked(isLocked);
        userService.add(userToAdd);
        return "redirect:/users";
    }

    @RequestMapping(
            value = "/admin/user/edit/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView getPageForUpdatingUser(@PathVariable(name = "id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", this.userService.get(id));
        modelAndView.addObject("roles", UserRole.values());
        modelAndView.addObject("is_admin", true);
        modelAndView.setViewName("edit_user");
        return modelAndView;
    }

    @RequestMapping(
            value = "/admin/user/update/{id}",
            method = RequestMethod.POST
    )
    public String updateUser(
            @PathVariable(name = "id") long id,
            @RequestParam(value = "username", defaultValue = "") String username,
            @RequestParam(value = "password", defaultValue = "") String password,
            @RequestParam(value = "role", defaultValue = "USER") UserRole role,
            @RequestParam(value = "locked", defaultValue = "false") boolean isLocked
    ) {
        User user = userService.get(id);
        user.setUsername(username);
        user.setPassword(passwordEncoder().encode(password));
        user.setRole(role);
        user.setLocked(isLocked);
        userService.update(user);
        return "redirect:/users";
    }

    @RequestMapping(
            value = "/admin/user/delete/{id}",
            method = RequestMethod.GET
    )
    public String deleteUser(@PathVariable(name = "id") long id) {
        userService.remove(id);
        return "redirect:/users";
    }

    @RequestMapping(
            value = "/admin/user/delete/all",
            method = RequestMethod.GET
    )
    public String deleteAllUsers() {
        userService.removeAll();
        return "redirect:/users";
    }
}
