package com.Task.MiniProject.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("errorMessage", "An unexpected error has occurred. Please try again.");
        return "error"; // Points to error.html in templates
    }

//    public String getErrorPath() {
//        return "/error";
//    }
}
