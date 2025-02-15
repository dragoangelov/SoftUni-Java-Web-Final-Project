package org.example.deliveryservice.web;

import jakarta.validation.Valid;
import org.example.deliveryservice.model.dto.UserRegistrationBindingDto;
import org.example.deliveryservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserRegisterController {

    private final UserService userService;

    public UserRegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerDto")
    public UserRegistrationBindingDto initRegisterDto() {
        return new UserRegistrationBindingDto();
    }

    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid UserRegistrationBindingDto registerDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){

            redirectAttributes.addFlashAttribute("registerDto",registerDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDto"
                    ,bindingResult);

            return "redirect:/users/register";

        }

        this.userService.registerUser(registerDto);

        return "redirect:/users/login";
    }

}

