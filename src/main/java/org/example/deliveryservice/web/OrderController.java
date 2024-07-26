package org.example.deliveryservice.web;

import jakarta.validation.Valid;
import org.example.deliveryservice.model.dto.OrderBindingDto;
import org.example.deliveryservice.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ModelAttribute("orderDto")
    public OrderBindingDto initOrderDto() {
        return new OrderBindingDto();
    }

    @GetMapping("/finalize")
    public String getFinalizeOrder(Model model,
                                   Principal principal) {

        model.addAttribute("foodPrice", this.orderService.getProductsPrice(principal.getName()));
        model.addAttribute("countProducts", this.orderService.getProductsInTheCart(principal.getName()).size());

        return "finalize-order";
    }

    @PostMapping("/finalize")
    public String postFinalizeOrder(@Valid OrderBindingDto orderDto,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    Principal principal) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("orderDto", orderDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderDto"
                    , bindingResult);

            return "redirect:/orders/finalize";

        }

        this.orderService.makeOrder(orderDto, principal.getName());

        return "redirect:/";
    }

    @GetMapping("/history")
    public String getOwnOrdersHistory(Model model,
                                      Principal principal) {

        model.addAttribute("orders", this.orderService.getOrdersByUser(principal.getName()));
        model.addAttribute("countProducts",this.orderService.getProductsInTheCart(principal.getName()).size());

        return "orders-history-user";
    }

    @GetMapping("/details/{id}")
    public String getOrderDetails(@PathVariable("id") Long id,
                                  Model model) {

        model.addAttribute("order", this.orderService.getOrderById(id));
        model.addAttribute("idAtr", id);

        return "order-details-api";
    }

    @GetMapping("/all/history")
    public String getAllOrdersHistory(Model model) {

        model.addAttribute("allOrders", this.orderService.getAllOrders());

        return "orders-history";
    }

    @PatchMapping("/finish/{id}")
    public String finishOrder(@PathVariable("id") Long orderId) {

        this.orderService.finishOrder(orderId);

        return "redirect:/orders/all/history";
    }

    @PatchMapping("/cancel/{id}")
    public String cancelOrder(@PathVariable("id") Long orderId) {

        this.orderService.cancelOrder(orderId);

        return "redirect:/orders/all/history";
    }

}
