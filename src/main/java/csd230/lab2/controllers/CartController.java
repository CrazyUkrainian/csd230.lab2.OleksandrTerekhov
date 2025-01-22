package csd230.lab2.controllers;

import csd230.lab2.entities.Cart;
import csd230.lab2.respositories.CartItemRepository;
import csd230.lab2.respositories.CartRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartItemRepository cartItemRepository;
    CartRepository cartRepository;
    public CartController(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @GetMapping
    public String cart(Model model) {
        Iterable<Cart> allCarts = cartRepository.findAll();
        // in a real app, we would get the cart for the current user
        Cart firstCart = allCarts.iterator().next();
        model.addAttribute("cart", firstCart);
        return "cart";
    }

    @GetMapping("/add-cart")
    public String cartForm(Model model) {
        model.addAttribute("cart", new Cart());
        return "add-cart";
    }

    @PostMapping("/add-cart")
    public String cartSubmit(@ModelAttribute Cart cart, Model model) {
        model.addAttribute("cart", cart);
        cartRepository.save(cart);
        model.addAttribute("carts", cartRepository.findAll());
        return "redirect:/cart";
    }
    @PostMapping("/remove-cart")
    public String remove_cartSubmit(@RequestParam(value = "cartItemId", required = false) Integer id, Model model) {
        cartItemRepository.removeById(Long.valueOf(id));
        return "redirect:/cart";
    }
//    @GetMapping("/edit-cart")
//    public String edit_cart(@RequestParam(value = "id", required = false) Integer id, Model model) {
//        model.addAttribute("cart", cartRepository.findById(id));
//        return "redirect:/cart";
//    }
//    @PostMapping("/edit-cart")
//    public String edit_cartSubmit(@ModelAttribute CartEntity cart, Model model) {
//        model.addAttribute("cart", cart);
//        cartRepository.save(cart);
//        model.addAttribute("carts", cartRepository.findAll());
//        return "redirect:/cart";
//    }

    // CartController.java
    @PostMapping("/selection")
    public String processSelection(@RequestParam("selectedCarts") List<Integer> selectedCartIds) {
        // Process the selected cart list here...
        System.out.println(selectedCartIds);
        for (Integer id : selectedCartIds) {
            Cart cart = cartRepository.findById(id);
            cartRepository.delete(cart);
        }

        return "redirect:/cart";
    }
}