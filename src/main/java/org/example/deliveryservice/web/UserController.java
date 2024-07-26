package org.example.deliveryservice.web;

import jakarta.validation.Valid;
import org.example.deliveryservice.model.dto.EditUserBindingDto;
import org.example.deliveryservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("editedUser")
    public EditUserBindingDto initEditUserDto() {
        return new EditUserBindingDto();
    }

    @GetMapping("/profile")
    public String getOwnProfile(Model model,
                                Principal principal) {

        model.addAttribute("user", this.userService.getUserViewByUsername(principal.getName()));
        model.addAttribute("countProducts",
                this.userService
                        .getUserByUsername(principal.getName()).getCart()
                        .getCountProducts());

        return "user-profile";
    }

    @GetMapping("/profile/{id}")
    public String getOtherUserProfileById(@PathVariable("id") Long id,
                                          Model model) {

        model.addAttribute("user", this.userService.getUserById(id));

        return "user-profile";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {

        model.addAttribute("users", this.userService.getAllUsers());

        return "all-users";
    }



    @GetMapping("/edit/{id}")
    public String getEditUser(@PathVariable("id") Long id,
                              Model model,
                              Principal principal) {

        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("countProducts",
                this.userService
                        .getUserByUsername(principal.getName()).getCart()
                        .getCountProducts());

        return "edit-user";
    }

    @PatchMapping("/edited/{id}")
    public String editedUser(@PathVariable("id") Long id,
                             @Valid EditUserBindingDto editedUser,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("editedUser", editedUser);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editedUser"
                    , bindingResult);

            return "redirect:/users/edit/{id}";

        }

        this.userService.editUser(id, editedUser);

        return "redirect:/users/profile";
    }

}