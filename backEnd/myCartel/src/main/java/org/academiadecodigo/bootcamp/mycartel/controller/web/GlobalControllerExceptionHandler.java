package org.academiadecodigo.bootcamp.mycartel.controller.web;

import org.academiadecodigo.bootcamp.mycartel.exceptions.MyCartelException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MyCartelException.class)
    public ModelAndView handleClientErrors(HttpServletRequest req, MyCartelException ex) {

        logException(ex);
        return handleError(HttpStatus.BAD_REQUEST, req, ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleServerErrors(HttpServletRequest req, Exception ex) {

        logException(ex);
        return handleError(HttpStatus.INTERNAL_SERVER_ERROR, req, ex);
    }

    private ModelAndView handleError(HttpStatus status, HttpServletRequest req, Exception ex) {

        ModelAndView model = new ModelAndView();

        model.addObject("error", status == HttpStatus.BAD_REQUEST ? ex.getMessage() : "please try again later");
        model.addObject("status", status);
        model.addObject("url", req.getRequestURL());
        model.addObject("timestamp", new Date().toString());
        model.addObject("exception", ex);

        model.setViewName("app-error");
        return model;
    }

    private void logException(Exception ex) {

        String errorOrigin = ex instanceof MyCartelException ? "Client" : "Server";

        String throwingClassName = ex.getStackTrace()[0].getClassName();
        String throwingMethodName = ex.getStackTrace()[0].getMethodName();


        Logger logger = LogManager.getLogger(throwingClassName);
        logger.error(errorOrigin + " error on " + throwingMethodName + "() - " + ex.getMessage());
    }
}
