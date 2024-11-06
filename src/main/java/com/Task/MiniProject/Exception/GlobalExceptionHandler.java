

package com.Task.MiniProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(IdAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleIdAlreadyExistsException(IdAlreadyExistException ex, Model model) {
        model.addAttribute("errorIdMessage", ex.getMessage()); // Set the error message
        return "newEmployee"; // Return to the newEmployee view
    }
    @ExceptionHandler(EmailAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleEmailAlreadyExistsException(EmailAlreadyExistException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "newEmployee";  // Create an error.html page to display error messages
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGeneralException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "An error occurred: " + ex.getMessage());
        return "error";
    }
}

