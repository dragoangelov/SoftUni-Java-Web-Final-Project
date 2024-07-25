package org.example.deliveryservice.web;

import org.example.deliveryservice.exception.WrongCategoryException;
import org.example.deliveryservice.model.enums.ProductCategoryEnum;
import org.example.deliveryservice.service.ProductService;
import org.example.deliveryservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final ProductService productService;
    private final UserService userService;

    public MenuController(ProductService productService,
                          UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @ExceptionHandler(WrongCategoryException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView categoryDoesNotExist(WrongCategoryException productNotFoundException) {

        ModelAndView modelAndView = new ModelAndView("category-does-not-exist");

        modelAndView.addObject("category", productNotFoundException.getCategory());

        return modelAndView;
    }
    @GetMapping
    public String getMenu(Principal principal,
                          Model model) {

        if (principal != null) {
            model.addAttribute("countProducts",
                    this.userService
                            .getUserByUsername(principal.getName())
                            .getCart().getCountProducts());
        }

        return "menu-categories";
    }

    @GetMapping("/{category}")
    public String getMenuCategoriesPage(@PathVariable("category")
                                        String category,
                                        Model model,
                                        Principal principal) {

        model.addAttribute("category", this.productService.findCategory(category));
        model.addAttribute("products", this.productService.getAllProductsByCategory(ProductCategoryEnum.valueOf(category)));

        if (principal != null) {
            model.addAttribute("countProducts",
                    this.userService
                            .getUserByUsername(principal.getName())
                            .getCart().getCountProducts());
        }

        return "categories-page";
    }

}
