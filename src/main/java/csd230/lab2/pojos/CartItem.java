package csd230.lab2.pojos;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link jpa_04.entities.CartItem}
 */
public abstract class CartItem implements Serializable, SaleableItem {
    private double price;
    private int quantity;
    private String description;
    private Cart cart;

    public CartItem() {
    }

    public CartItem(double price, int quantity, String description, Cart cart) {
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.cart = cart;
        cart.addItem(this);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "price=" + price +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", cart=" + cart +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem cartItem)) return false;
        return Double.compare(getPrice(), cartItem.getPrice()) == 0 && getQuantity() == cartItem.getQuantity() && Objects.equals(getDescription(), cartItem.getDescription()) && Objects.equals(getCart(), cartItem.getCart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getQuantity(), getDescription(), getCart());
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
