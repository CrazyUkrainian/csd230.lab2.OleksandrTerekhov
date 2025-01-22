package csd230.lab2.pojos;

import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link jpa_04.entities.Cart}
 */
/**
 * DTO for {@link jpa_04.entities.CartI}
 */
public class Cart {
    private List<CartItem> items;

    public Cart() {
    }

    public Cart(List<CartItem> items) {
        this.items = items;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart cart)) return false;
        return Objects.equals(getItems(), cart.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getItems());
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}
