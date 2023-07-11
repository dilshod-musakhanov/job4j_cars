package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("signUp")
    public String signUp() {
        return "user/add";
    }

    @PostMapping("addNew")
    public String addNewUser(Model model, @ModelAttribute User user) {
        var optionalUser = userService.save(user);
        if (optionalUser.isEmpty()) {
            model.addAttribute(
                    "message",
                    "Invalid input so try sign up again! Or an account might already exist. Please use log in option then!");
            return "user/add";
        }
        return "redirect:/users/loginForm";
    }

    @GetMapping("loginForm")
    public String loginForm() {
        return "user/login";
    }


    @PostMapping("login")
    public String login(Model model, @ModelAttribute User user, HttpServletRequest request) {
        var optionalUser = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (optionalUser.isEmpty()) {
            model.addAttribute(
                    "message",
                    "Incorrect input details so try to log in again! Or account might not exist so try to sign up then!");
            return "user/login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", optionalUser.get());
        return "redirect:/posts/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/loginForm";
    }
}
