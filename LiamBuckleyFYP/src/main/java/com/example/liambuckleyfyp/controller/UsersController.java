//Below code is taken from a YouTube tutorial by Java Master (2021) on how to create a user register and login page
package com.example.liambuckleyfyp.controller;

import jakarta.servlet.http.HttpSession;
import com.example.liambuckleyfyp.service.UsersService;
import org.springframework.ui.Model;
import com.example.liambuckleyfyp.model.UsersModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    private final UsersService usersService;

    // Constructor to inject UsersService dependency
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // Handles GET requests to "/register" and returns the registration page
    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UsersModel());
        return "register_page";
    }

    // Handles GET requests to "/login" and returns the login page
    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }

    // Handles POST requests to "/register" and processes user registration
    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel){
        System.out.println("register request: " + usersModel);
        UsersModel registeredUser = usersService.registerUser(usersModel.getLogin(), usersModel.getPassword(), usersModel.getEmail());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    // Handles POST requests to "/login" and processes user login
    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model, HttpSession session){
        System.out.println("login request: " + usersModel);
        UsersModel authenticated = usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());
        if(authenticated != null){
            session.setAttribute("userLogin", authenticated.getLogin());
            return "redirect:/golfcourses";
        } else {
            return "error_page";
        }
    }
}

//References
// Java Master (2021). Java Web Project | Create Login and Register Form From Scratch with, Java11, Spring MVC, PostgreSQL. [online] YouTube. Available at: https://www.youtube.com/watch?v=x_nfnVU0wAI [Accessed 2 Nov. 2024].