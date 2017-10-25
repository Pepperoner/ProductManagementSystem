package com.management.product.controller;

import org.apache.log4j.Logger;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AdviceController {

    public static final Logger logger = Logger.getLogger(AdviceController.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView NoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView NullPointerException(
            NullPointerException ex,
            HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ModelAndView IllegalArgumentException(
            IllegalArgumentException ex,
            HttpServletRequest request) {

        return handleException(ex, request, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ModelAndView HttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex,
            HttpServletRequest request) {

        return handleException(ex, request, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ModelAndView IllegalAccessException(
            IllegalAccessException ex,
            HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IllegalMappingException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ModelAndView IllegalMappingException(
            IllegalMappingException ex,
            HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView Exception(
            Exception ex,
            HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ModelAndView prepareModelAndView(HttpStatus status, String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);
        modelAndView.addObject("status", status.value());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    private ModelAndView handleException(
            Exception ex,
            HttpServletRequest request,
            HttpStatus status
    ) {
        logRequest(request);
        logException(ex);
        return prepareModelAndView(status, ex.getClass().getSimpleName() +
                " : " + ex.getMessage());
    }

    private static void logException(Exception ex) {
        logger.error(ex.getMessage(), ex);
        ex.printStackTrace();
    }

    private static void logRequest(final HttpServletRequest request) {
        if (request != null) {
            logger.error(request.getRemoteAddr() + " : " +
                    request.getRequestURI());
        }
    }
}
