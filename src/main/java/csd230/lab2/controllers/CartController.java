package csd230.lab2.controllers;

import csd230.lab2.entities.Cart;
import csd230.lab2.entities.CartItem;
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
    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @GetMapping
    public String cart(Model model) {
        Iterable<Cart> allCarts = cartRepository.findAll();
        // In a real application, you would get the cart for the current user
        Cart firstCart = allCarts.iterator().next();  // Grab the first cart for demo
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
        cartRepository.save(cart);  // Save the new cart
        model.addAttribute("carts", cartRepository.findAll());
        return "redirect:/cart";
    }

    @PostMapping("/remove-cart")
    public String removeCartSubmit(@RequestParam(value = "cartItemId", required = false) Long id) {
        cartItemRepository.deleteById(id);  // Deletes the cart item by ID
        return "redirect:/cart";
    }

    @PostMapping("/selection")
    public String processSelection(@RequestParam("selectedCarts") List<Long> selectedCartIds) {
        // Delete selected carts based on the list of IDs
        for (Long id : selectedCartIds) {
            Cart cart = cartRepository.findById(id).orElse(null); // Find cart by ID
            if (cart != null) {
                cartRepository.delete(cart);  // Delete the cart
            }
        }

        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        Iterable<Cart> allCarts = cartRepository.findAll();
        Cart firstCart = allCarts.iterator().next();  // Grab the first cart for demo
        double totalAmount = firstCart.getItems().stream()
                .mapToDouble(CartItem::getPrice)
                .sum();
        model.addAttribute("totalAmount", totalAmount);
        return "checkout";
    }

    @PostMapping("/checkout")
    public String processCheckout() {
        // Perform any necessary checkout actions here
        return "redirect:/cart/checkout";  // Redirect to the cart or a confirmation page
    }
    @PostMapping("/process-payment")
    public String processPayment(
            @RequestParam("cardNumber") String cardNumber,
            @RequestParam("expiryDate") String expiryDate,
            @RequestParam("cvv") String cvv,
            Model model) {

        // Simulate payment processing (for demo purposes)
        boolean transactionApproved = simulatePayment(cardNumber, expiryDate, cvv);

        // Add the transaction status to the model
        model.addAttribute("transactionApproved", transactionApproved);

        // Calculate the total amount again (optional)
        Iterable<Cart> allCarts = cartRepository.findAll();
        Cart firstCart = allCarts.iterator().next();
        double totalAmount = firstCart.getItems().stream()
                .mapToDouble(CartItem::getPrice)
                .sum();
        model.addAttribute("totalAmount", totalAmount);

        return "checkout";  // Return to the checkout page to display the result
    }

    private boolean simulatePayment(String cardNumber, String expiryDate, String cvv) {
        // Simulate a payment process (e.g., validate card details)
        // For demo purposes, approve the transaction if the card number is 16 digits
        return cardNumber != null && cardNumber.length() == 16;
    }

}
