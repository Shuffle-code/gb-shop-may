package ru.gb.gbshopmay.web.conntroller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.gbshopmay.service.CartService;
import ru.gb.gbshopmay.web.model.Cart;

import javax.servlet.http.HttpSession;

/**
 * @author Artem Kropotov
 * created at 26.06.2022
 **/

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;

    @GetMapping("/fill")
    public String fill(Model model, HttpSession httpSession) {
        Cart cart = cartService.getCurrentCart(httpSession);
        model.addAttribute("cart", cart);
        return "order/order-form";
    }
}
