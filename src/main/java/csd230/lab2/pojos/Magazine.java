package csd230.lab2.pojos;

import java.util.Date;
import java.util.Objects;

/**
 * @author fcarella
 */
/**
 * DTO for {@link jpa_04.entities.Magazine}
 */
public class Magazine extends Publication {

    private int orderQty;
    private Date currIssue;
    public Magazine() {
    }

    public Magazine(double price, int quantity, String description, Cart cart, String title, int copies, int orderQty, Date currIssue) {
        super(price, quantity, description, cart, title, copies);
        this.orderQty = orderQty;
        this.currIssue = currIssue;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "orderQty=" + orderQty +
                ", currIssue=" + currIssue +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Magazine magazine)) return false;
        if (!super.equals(o)) return false;
        return getOrderQty() == magazine.getOrderQty() && Objects.equals(getCurrIssue(), magazine.getCurrIssue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOrderQty(), getCurrIssue());
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public Date getCurrIssue() {
        return currIssue;
    }

    public void setCurrIssue(Date currIssue) {
        this.currIssue = currIssue;
    }




}