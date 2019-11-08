package application.controller;

import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import application.model.User;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @GetMapping("/register")
    public ModelAndView signUp(){
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("register");
        return model;
    }

    @PostMapping("/register")
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();
        User userExist = userService.findByUsername(user.getUsername());

        if (userExist != null){
            bindingResult.rejectValue("user_login", "error.user", "This user already exist!");
        }
        if (bindingResult.hasErrors()){
            model.setViewName("register");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been register successfully!");
            model.addObject("user", new User());
            model.setViewName("register");
        }
        return model;
    }

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());

        model.addObject("userName", user.getUsername());
        model.setViewName("index");
        return model;
    }

    @GetMapping("/404")
    public ModelAndView accessDenied(){
        ModelAndView model = new ModelAndView();
        model.setViewName("404");
        return model;
    }

}
