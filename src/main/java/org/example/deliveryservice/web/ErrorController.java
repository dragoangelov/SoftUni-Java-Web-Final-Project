package org.example.deliveryservice.web;

import org.example.deliveryservice.exception.NotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView onProductNotFound(NotFoundException notFoundException){

        ModelAndView modelAndView=new ModelAndView("object-not-found");

        modelAndView.addObject("objectId",notFoundException.getObjectId());
        modelAndView.addObject("objectType",notFoundException.getObjectType());

        return modelAndView;
    }

}
