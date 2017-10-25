package com.management.product.controller;

import com.management.product.service.ProductService;
import com.management.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan(basePackages = "com.management.product.service")
public class MainController {

    private ProductService productService;

    private UserService userService;

    @Autowired
    public MainController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @RequestMapping(
            value = {"", "/", "/index", "home"},
            method = RequestMethod.GET
    )
    public ModelAndView getIndexPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", productService.getAll());
        modelAndView.addObject("is_admin", userService.isAuthenticatedAdmin());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(
            value = "/users",
            method = RequestMethod.GET
    )
    public ModelAndView getUserPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.getAll());
        modelAndView.addObject("is_admin", userService.isAuthenticatedAdmin());
        modelAndView.setViewName("users");
        return modelAndView;
    }
    @RequestMapping(
            value = "product/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView getProductPage(@PathVariable(name = "id") long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", productService.get(id));
        modelAndView.addObject("is_admin", userService.isAuthenticatedAdmin());
        modelAndView.setViewName("product");
        return modelAndView;
    }
}
