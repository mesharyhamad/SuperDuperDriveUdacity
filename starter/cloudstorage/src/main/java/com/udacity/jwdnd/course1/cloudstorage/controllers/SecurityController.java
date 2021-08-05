package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {
    private UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    String login(){
        return "login";
    }

    @GetMapping("/signup")
    String signup(){
        return "signup";
    }

    @PostMapping("/signup")
    String createUser(@ModelAttribute User user, Model model){
        Boolean noError = true;

        if(userService.userExists(user.getUsername())){
          noError=false;
        }
        if(noError){
          Integer id= userService.createUser(user);
          if(id ==null){
              noError=false;
          }
        }
        if(noError){
             model.addAttribute("signupTrue",true);
        }
        else{
            model.addAttribute("signupError",true);
        }

        return "signup";
    }

//    @GetMapping("/logout")
//    String logout(){
//
//        return "login";
//    }
}
