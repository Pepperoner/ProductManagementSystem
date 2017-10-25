package com.management.product.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthorizationController {

    @RequestMapping(
            value = "/login",
            method = RequestMethod.GET
    )
    public String loginPage(){
        return "login";
    }

    @RequestMapping(
            value = "/loguout",
            method = RequestMethod.POST
    )
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(
                request,response, SecurityContextHolder.getContext().getAuthentication()
        );
        return "redirect:/login?logout";
    }
}
