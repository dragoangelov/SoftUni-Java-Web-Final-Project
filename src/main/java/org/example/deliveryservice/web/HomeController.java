package org.example.deliveryservice.web;

import org.example.deliveryservice.model.entity.UserEntity;
import org.example.deliveryservice.service.OrderService;
import org.example.deliveryservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    private final UserService userService;
    private final OrderService orderService;

    public HomeController(UserService userService,
                          OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String getHome(Principal principal,
                          Model model) {

        if (principal != null) {

            final UserEntity loggedUser = this.userService.getUserByUsername(principal.getName());

            model.addAttribute("name", loggedUser.getUsername());
            model.addAttribute("countProducts", loggedUser.getCart().getCountProducts());

        }

        return "index";
    }

}
