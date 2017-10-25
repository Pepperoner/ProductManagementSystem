package com.management.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class IllegalAccessController {

    @RequestMapping(
            value = "/illegal_access_exception",
            method = RequestMethod.GET
    )
    public void getIllegalAccessExceprion() throws IllegalAccessException{
        throw  new IllegalAccessException("The user doesn`t have rights to access the request");
    }
}
